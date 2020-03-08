package com.chuanze.crud.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrieveTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据 Id 进行查询
     */
    @Test
    public void selectById() {
        UserEntity user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    /**
     * 根据 Ids 进行查询
     */
    @Test
    public void selectByIds() {
        List<Long> idsList = Arrays.asList(1094592041087729666L
                , 1088248166370832385L, 1088250446457389058L);
        List<UserEntity> user = userMapper.selectBatchIds(idsList);
        System.out.println(user);
    }

    /**
     * 根据 Map来查询 进行查询
     * 1. key 为数据库表的字段名称,而不是实体中的列名
     * 2. value 查询的值
     */
    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", 25);
        List<UserEntity> user = userMapper.selectByMap(map);
        System.out.println(user);
    }

    /** ↓↓↓↓↓↓-条件查询构造器-↓↓↓↓↓↓ **/

    /**
     * 1、名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapper1() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        //QueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>query();
        queryWrapper.like("name", "雨")
                .lt("age", 40);
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 2、名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨")
                .between("age", 20, 40)
                .isNotNull("email");
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 3、名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age>=25 order by age desc,id asc
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .or().ge("age", 25)
                .orderByAsc("id");
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 4、创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'");
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 5、名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 6、名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 7、（年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 8、年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 9、只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35))
                .last("limit 1");// 注意该函数有 sql 注入的风险
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    /**
     * 10、名字中包含雨并且年龄小于40(需求1加强版)
     * 第一种情况：select id,name
     * from user
     * where name like '%雨%' and age<40
     * 第二种情况：select id,name,age,email
     * from user
     * where name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapper10_1() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        //QueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>query();
        queryWrapper.select("id", "name")
                .like("name", "雨")
                .lt("age", 40);
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void selectByWrapper10_2() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        //QueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>query();
        queryWrapper
                .like("name", "雨")
                .lt("age", 40)
                .select(UserEntity.class
                        , info -> !info.getColumn().equals("create_time")
                                && !info.getColumn().equals("manager_id"));
        List<UserEntity> user = userMapper.selectList(queryWrapper);
        System.out.println(user);
    }


}
