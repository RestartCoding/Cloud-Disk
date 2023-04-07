package com.xb.cloud.disk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xb.cloud.disk.dao.UserMapper;
import com.xb.cloud.disk.entity.User;
import com.xb.cloud.disk.exception.BusinessException;
import com.xb.cloud.disk.service.UserService;
import com.xb.cloud.disk.support.PasswordEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author jack
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void signUp(User user) throws BusinessException {
        User userByUsername = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (userByUsername != null) {
            throw new BusinessException("username already exists.");
        }
        User copy = new User();
        BeanUtils.copyProperties(user, copy);
        copy.setPassword(PasswordEncoder.encode(user.getPassword()));
        baseMapper.insert(copy);
    }

    @Override
    public void signIn(User user) throws BusinessException {
        User userByUsernameAndPassword = baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, PasswordEncoder.encode(user.getPassword())));
        if (userByUsernameAndPassword == null) {
            throw new BusinessException("incorrect username or password.");
        }
    }
}