package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * 分页
 * 使用分页的时候要先配置。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectPage() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 26);
        Page<UserEntity> page = new Page<>(1, 2,true);
        IPage<UserEntity> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总记录数：" + iPage.getTotal());
        System.out.println("总记录数：" + iPage.getSize());
        List<UserEntity> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectMapPage() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 26);
        Page<Map<String,Object>> page = new Page<>(1, 2,true);
        IPage<Map<String,Object>> iPage = userMapper.selectMapsPage(page, queryWrapper);
        System.out.println("总记录数：" + iPage.getTotal());
        System.out.println("总记录数：" + iPage.getSize());
        List<Map<String,Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }


    /**
     * 查询自定义 sql 分页（可用于多表联表查询）
     */
    @Test
    public void selectMyPage() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 26);
        Page<UserEntity> page = new Page<>(1, 2,true);
        IPage<UserEntity> iPage = userMapper.selectUserPage(page, queryWrapper);
        System.out.println("总记录数：" + iPage.getTotal());
        System.out.println("总记录数：" + iPage.getSize());
        List<UserEntity> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }
}
