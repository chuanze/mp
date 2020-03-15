package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void updateById() {
        UserEntity user = new UserEntity();
        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数" + rows);
    }

    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void updateByWrapper() {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟")
                .eq("age", 28);

        UserEntity user = new UserEntity();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);
        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 条件构造器中传实体，会出现在 where 条件中
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void updateByWrapperEntity() {
        UserEntity whereUser = new UserEntity();
        whereUser.setName("李艺伟"); // sql 会是 like ，因为在该字段中设置了
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟")
                .eq("age", 28);

        UserEntity user = new UserEntity();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);
        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 如果修改实体很多，但是只需修改一两个，可以使用 set 方法
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void updateByWrapperBySet() {
        UserEntity whereUser = new UserEntity();
        whereUser.setName("李艺伟"); // sql 会是 like ，因为在该字段中设置了
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟")
                .eq("age", 28)
                .set("age", 29);// 通过set更新字段

        int rows = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数" + rows);
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

}
