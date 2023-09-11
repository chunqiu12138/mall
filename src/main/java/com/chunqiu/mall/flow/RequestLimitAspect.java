package com.chunqiu.mall.flow;

import com.chunqiu.mall.common.utils.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Log4j2
public class RequestLimitAspect extends HttpServlet {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    HttpServletRequest request;

    // 切点
    @Pointcut("@annotation(requestLimit)")
    public void controllerAspect(RequestLimit requestLimit) {}

    @Around("controllerAspect(requestLimit)")
    public Object doAround(ProceedingJoinPoint joinPoint, RequestLimit requestLimit) throws Throwable {
        // get parameter from annotation
        long period = requestLimit.period();
        long limitCount = requestLimit.count();

        // request 信息
        String ip = RequestUtil.getClientIpAddress(request);
        String uri = RequestUtil.getRequestUri(request);
        String key = "req_limit_".concat(uri).concat(ip);

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        // 添加时间戳
        long currentMs = System.currentTimeMillis();
        zSetOperations.add(key, currentMs, currentMs);

        // set the expiration time for the code user
        redisTemplate.expire(key, period, TimeUnit.SECONDS);

        // remove the value that out of current window
        zSetOperations.removeRangeByScore(key, 0, currentMs - period * 1000);

        // check all available count
        Long count = zSetOperations.zCard(key);

        if (count > limitCount) {
            log.error("接口拦截：{} 请求超过限制频率【{}次/{}s】,IP为{}", uri, limitCount, period, ip);
            throw new Exception("500");
        }

        // execute the user request
        return  joinPoint.proceed();
    }

}
