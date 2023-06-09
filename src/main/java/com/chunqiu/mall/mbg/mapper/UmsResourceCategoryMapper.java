package com.chunqiu.mall.mbg.mapper;

import com.chunqiu.mall.mbg.model.UmsResourceCategory;
import com.chunqiu.mall.mbg.model.UmsResourceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsResourceCategoryMapper {
    int countByExample(UmsResourceCategoryExample example);

    int deleteByExample(UmsResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceCategory record);

    int insertSelective(UmsResourceCategory record);

    List<UmsResourceCategory> selectByExample(UmsResourceCategoryExample example);

    UmsResourceCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);

    int updateByExample(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);

    int updateByPrimaryKeySelective(UmsResourceCategory record);

    int updateByPrimaryKey(UmsResourceCategory record);
}