package com.chunqiu.mall.dao;

import com.chunqiu.mall.dto.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EsProductDao {
    /**
     * 获取指定ID的搜索商品
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}

