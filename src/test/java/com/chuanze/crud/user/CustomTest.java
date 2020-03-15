package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
 * 自定义 sql 实现
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomTest {
    @Autowired
    private UserMapper userMapper;


    /**
     * 5、名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectMy() {
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = Wrappers.<UserEntity>lambdaQuery();
        lambdaQueryWrapper.likeRight(UserEntity::getName, "王")
                .and(lqw -> lqw.lt(UserEntity::getAge, 40)
                        .or().isNotNull(UserEntity::getEmail));
        List<UserEntity> userList = userMapper.selectAll(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }
}
