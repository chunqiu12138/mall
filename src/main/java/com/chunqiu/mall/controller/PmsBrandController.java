package com.chunqiu.mall.controller;


import com.chunqiu.mall.common.CommonPage;
import com.chunqiu.mall.common.CommonResult;
import com.chunqiu.mall.mbg.model.PmsBrand;
import com.chunqiu.mall.service.PmsBrandService;
import com.chunqiu.mall.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * 品牌管理Controller
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RedisService redisService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(demoService.listAllBrand());
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        PmsBrand brand = null;
        String key = new String("brand:" + id);
        //如果缓存命中，则取出
        if(redisService.hasKey(key)) {
            try {
                brand = (PmsBrand) objectMapper.readValue(redisService.get(key), PmsBrand.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //未命中，从数据中取出，设置到缓存，并设置过期时间
        else {
            brand = demoService.getBrand(id);
            //设置缓存
            try {
                redisService.set(key, objectMapper.writeValueAsString(brand));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //设置过期时间
            redisService.expire(key, 30);
        }
        return CommonResult.success(brand);
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        //id对应redis的key
        String key = new String("brand:" + id);
        CommonResult commonResult;
        int count = demoService.updateBrand(id, pmsBrandDto);
        //删除缓存
        redisService.remove(key);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }
}
