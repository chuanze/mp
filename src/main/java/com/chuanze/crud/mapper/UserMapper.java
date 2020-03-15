package com.chuanze.crud.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuanze.crud.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chuanze
 * @since 2020-03-15
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    //@Select("select * from user ${ew.customSqlSegment}")
    List<UserEntity> selectAll(@Param(Constants.WRAPPER) Wrapper<UserEntity> wrapper);

    IPage<UserEntity> selectUserPage(Page<UserEntity> page, @Param(Constants.WRAPPER)Wrapper<UserEntity> wrapper);
}
