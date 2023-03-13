package com.chunqiu.mall;

import com.chunqiu.mall.dao.ProductMapper;
import com.chunqiu.mall.dao.UmsAdminRoleRelationDao;
import com.chunqiu.mall.mbg.mapper.UmsAdminMapper;
import com.chunqiu.mall.mbg.mapper.UmsRolePermissionRelationMapper;
import com.chunqiu.mall.mbg.model.UmsAdmin;
import com.chunqiu.mall.mbg.model.UmsAdminRoleRelation;
import com.chunqiu.mall.mbg.model.UmsRolePermissionRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MallApplicationTests {

    @Autowired
    UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Test
    void contextLoads() {
        for (int i = 10; i <= 27; i++) {
            UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
            relation.setRoleId(new Long(5));
            relation.setPermissionId(new Long(i - 9));
            System.out.println(umsAdminMapper);
            umsRolePermissionRelationMapper.insert(relation);
            System.out.println(relation.getId());
        }

    }

    void updatePassword() {

    }


}
