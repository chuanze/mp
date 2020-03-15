package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.service.IUserService;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private IUserService userService;


    /**
     * 注意，其是可以设置返回多条数据时是否报错
     * 1. true ，返回多条数据的时候，会报错
     * 2. false，返回多条数据只取第一条，不会报错
     */
    @Test
    public void getOne() {
        UserEntity one = userService.getOne(Wrappers.<UserEntity>lambdaQuery(), false);
        System.out.println(one);
    }

    /**
     * 批量处理，可以设置每次插入多少条
     * 1. 避免大量 sql 无法执行的情况
     * 2. 避免一下子进行批量插入导致内存泄漏
     */
    @Test
    @Transactional
    public void saveBatch() {
        UserEntity user1 = new UserEntity();
        user1.setName("张三");
        user1.setAge(20);

        UserEntity user2 = new UserEntity();
        user2.setName("李四");
        user2.setAge(21);

        List<UserEntity> userList = Arrays.asList(user1, user2);
        boolean saveBatch = userService.saveBatch(userList, 1000);
        System.out.println(saveBatch);
    }

    @Test
    @Transactional
    public void saveOrUpdateBatch() {
        UserEntity user1 = new UserEntity();
        user1.setName("张三");
        user1.setAge(20);

        UserEntity user2 = new UserEntity();
        user2.setName("王五");
        user2.setAge(21);

        List<UserEntity> userList = Arrays.asList(user1, user2);
        boolean saveBatch = userService.saveOrUpdateBatch(userList, 1000);
        System.out.println(saveBatch);
    }

    @Test
    public void queryChain() {
        // 返回多条数据的时候会报错
        UserEntity one = userService.lambdaQuery().eq(UserEntity::getName, "李艺伟")
                .eq(UserEntity::getAge, 21)
                .one();
        List<UserEntity> list = userService.lambdaQuery()
                .ge(UserEntity::getAge, 10)
                .list();
        System.out.println(one);
        System.out.println(list);
    }

    @Test
    @Transactional
    public void updateChain() {
        boolean flag = userService.lambdaUpdate().eq(UserEntity::getName,"李四")
                .update();
        System.out.println(flag);
    }

    @Test
    @Transactional
    public void deleteChain() {
        boolean flag = userService.lambdaUpdate().eq(UserEntity::getName,"李四")
                .remove();
        System.out.println(flag);
    }
}
