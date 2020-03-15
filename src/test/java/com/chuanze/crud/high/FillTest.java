package com.chuanze.crud.high;

import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 自动填充
 * 1. 要先配置 MyMetaObjectHander
 * 2. 在字段中设置 @TableField(fill = FieldFill.UPDATE)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FillTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        UserEntity user = new UserEntity();
        user.setName("赵六");
        user.setAge(31);
        user.setEmail("zl@baomidou.com");
        user.setManagerId(1088248166370832385L);
        int rows = userMapper.insert(user);
        System.out.println("影响函数：" + rows);
    }

    @Test
    public void update() {
        UserEntity user = new UserEntity();
        user.setId(1239211867214245889L);
        user.setName("依依");
        int rows = userMapper.updateById(user);
        System.out.println("影响函数：" + rows);
    }

}
