package com.chunqiu.mall.mbg.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.chunqiu.mall.mbg.mapper", "com.chunqiu.mall.dao"})
public class MyBatisConfig {
}

