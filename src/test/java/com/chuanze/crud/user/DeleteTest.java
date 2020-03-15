package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void deleteById() {
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响记录数" + rows);
    }

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "张雨琪");
        columnMap.put("age", 25);
        int rows = userMapper.deleteByMap(columnMap);
        System.out.println("影响记录数" + rows);
    }

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void deleteBatchIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1088250446457389058L
                , 1094590409767661570L
                , 1094592041087729666L));
        System.out.println("影响记录数" + rows);
    }

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void deleteByWrapper() {
        // 如果传入实体，会作为查询条件
        QueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>query();
        queryWrapper.eq("name", "李艺伟")
                .eq("age", 30);
        int rows = userMapper.delete(queryWrapper);
        System.out.println("影响记录数" + rows);

    }

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void deleteByLambda() {
        // 如果传入实体，会作为查询条件
        LambdaQueryWrapper<UserEntity> lambdaQuery = Wrappers.<UserEntity>lambdaQuery();
        lambdaQuery.eq(UserEntity::getAge, 27)
                .or()
                .gt(UserEntity::getAge, 41);
        int rows = userMapper.delete(lambdaQuery);
        System.out.println("影响记录数" + rows);

    }
}
