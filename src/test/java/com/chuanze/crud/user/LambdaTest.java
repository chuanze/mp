package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 1、名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectLambd() {
        // 创建 Lambda 表达条件构造器方法
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new QueryWrapper<UserEntity>().lambda();
        //LambdaQueryWrapper<UserEntity> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        //LambdaQueryWrapper<UserEntity> lambdaQueryWrapper2 = Wrappers.<UserEntity>lambdaQuery();
        lambdaQueryWrapper.like(UserEntity::getName, "雨")
                .lt(UserEntity::getAge, 40);
        List<UserEntity> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 5、名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectLambd2() {
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = Wrappers.<UserEntity>lambdaQuery();
        lambdaQueryWrapper.likeRight(UserEntity::getName, "王")
                .and(lqw -> lqw.lt(UserEntity::getAge, 40)
                        .or().isNotNull(UserEntity::getEmail));
        List<UserEntity> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 3.0 新增的方式
     */
    @Test
    public void selectLambd3() {
        List<UserEntity> userList = new LambdaQueryChainWrapper<UserEntity>(userMapper)
                .like(UserEntity::getName, "雨")
                .ge(UserEntity::getAge, 20)
                .list();
        userList.forEach(System.out::println);
    }

    /**
     * Lambda 表达式修改方法
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void updateByLambda() {
        LambdaUpdateWrapper<UserEntity> lambdaQueryWrapper = Wrappers.<UserEntity>lambdaUpdate();
        lambdaQueryWrapper.eq(UserEntity::getName, "李艺伟")
                .eq(UserEntity::getAge, 29)
                .set(UserEntity::getAge, 30);
        int rows = userMapper.update(null, lambdaQueryWrapper);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 链式 lambda 表达式
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void updateByLambdaChin() {
        boolean flag = new LambdaUpdateChainWrapper<UserEntity>(userMapper)
                .eq(UserEntity::getName, "李艺伟")
                .eq(UserEntity::getAge, 30)
                .set(UserEntity::getAge, 31)
                .update();
        System.out.println("是否成功：" + flag);
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
