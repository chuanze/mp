package com.chuanze.crud.service.impl;

import com.chuanze.crud.entity.UserEntity;
import com.chuanze.crud.mapper.UserMapper;
import com.chuanze.crud.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chuanze
 * @since 2020-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
