package com.chuanze.crud.user;

import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入失败情况一：可能是主键的设置问题
     *
     * @TableId(value = "id", type = IdType.ASSIGN_ID)
     * 1. 主键不是id，要通过 @TableId 来标注那个是主键
     * 2. 主键生成规则设置不对，可以设置主键的 IdType 属性
     */
    @Test
    @Transactional// 想在数据库查看到结果，得去掉该注解
    public void insertOne() {
        UserEntity user = new UserEntity();
        user.setName("刘明强");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数" + rows);
    }

    /**
     * 插入失败情况二：实体类中有比数据库列多的字段，没有做处理
     * 1. 可以将多余的字段声明为 transient，序列化时，不会输出该字段
     * 2. 将改字段声明为 static 静态属性
     * 3. 使用 @TableField(exist=false) 来实现
     */
    @Test
    @Transactional//想在数据库查看到结果，得去掉该注解
    public void insertTwo() {
        UserEntity user = new UserEntity();
        user.setName("刘明强");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        //user.setRemark("这里是备注，数据库表中并不存在该字段!");
        int rows = userMapper.insert(user);
        System.out.println("影响记录数" + rows);
    }
}
