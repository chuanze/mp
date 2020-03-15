package com.chuanze.crud.high;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 逻辑删除
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicDeleteTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响行数：" + rows);
    }

    /**
     * 会自动加上 deleted = 0 过滤条件
     */
    @Test
    public void select() {
        List<UserEntity> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    /**
     * 会自动加上 deleted = 0 过滤条件
     */
    @Test
    public void updateById() {
        UserEntity user = new UserEntity();
        user.setAge(26);
        user.setId(1088248166370832385L);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数：" + rows);
    }


    /**
     * 注意，自定义的 sql 不会自动加上 deleted = 0 过滤条件
     */
    @Test
    public void mySelect() {
        List<UserEntity> userList = userMapper.mySelectList(Wrappers.<UserEntity>lambdaQuery()
                .gt(UserEntity::getAge, 25));
        userList.forEach(System.out::println);
    }
}
