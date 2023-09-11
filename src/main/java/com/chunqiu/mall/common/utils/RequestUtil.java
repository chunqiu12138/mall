package com.chunqiu.mall.common.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    /**
     * 获取请求的IP地址
     * @param request HttpServletRequest对象
     * @return 请求的IP地址
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String clientIpAddress = request.getRemoteAddr();
        return clientIpAddress;
    }

    /**
     * 获取请求的URI
     * @param request HttpServletRequest对象
     * @return 请求的URI
     */
    public static String getRequestUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return requestUri;
    }
}