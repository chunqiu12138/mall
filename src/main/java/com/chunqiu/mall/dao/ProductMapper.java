package com.chunqiu.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

public interface ProductMapper {
    String getNameById(Integer id);
}
