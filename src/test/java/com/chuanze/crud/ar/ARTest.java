package com.chuanze.crud.ar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chuanze.crud.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void insert() {
        UserEntity user = new UserEntity();
        user.setId(1088248166370832383L);
        user.setName("向前");
        user.setAge(25);
        user.setEmail("xq@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        boolean flag = user.insert();
        System.out.println(flag);
    }

    /**
     * 新增或者修改，如果设置了id，就修改，否则就是新增
     * 1. 首先会先查询是否存在
     * 2. 然后再
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void insertOrUpdate() {
        UserEntity user = new UserEntity();
        //user.setId(1094590409767661570L);
        user.setName("李艺彤");
        boolean flag = user.insertOrUpdate();
        System.out.println(flag);
    }

    /**
     * 返回的是一个全新的对象
     */
    @Test
    public void selectById1() {
        UserEntity user = new UserEntity();
        UserEntity userSelect = user.selectById(1094590409767661570L);
        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }

    /**
     * 返回的是一个全新的对象
     */
    @Test
    public void selectById2() {
        UserEntity user = new UserEntity();
        user.setId(1094590409767661570L);
        UserEntity userSelect = user.selectById();
        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }

    /**
     * 注意：这里的 selectOne 查询到多条的时候，不会报错
     */
    @Test
    public void selectOne() {
        UserEntity user = new UserEntity();
        user.setId(1094590409767661570L);// 不作为参数，没有用
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "李艺伟");
        UserEntity userSelect = user.selectOne(queryWrapper);
        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }

    /**
     * 注意，除了设置id之外，必须还要多设置一个属性，否则会报 sql 错误
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void udpateById() {
        UserEntity user = new UserEntity();
        user.setId(1094590409767661570L);
        user.setName("李四");
        boolean flag = user.updateById();
        System.out.println(flag);
    }

    /**
     * 注意，除了设置id之外，必须还要多设置一个属性，否则会报 sql 错误
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void udpateByWrapper() {
        UserEntity user = new UserEntity();
        user.setId(1094590409767661570L);
        user.setName("李四");
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟");
        boolean flag = user.update(updateWrapper);
        System.out.println(flag);
    }

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void deleteById() {
        UserEntity user = new UserEntity();
        user.setId(1094590409767661570L);
        boolean flag = user.deleteById();
        System.out.println(flag);
    }

}
