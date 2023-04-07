package com.xb.cloud.disk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xb.cloud.disk.entity.User;
import com.xb.cloud.disk.exception.BusinessException;

/**
 * @author jack
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param user user
     * @throws BusinessException BusinessException
     */
    void signUp(User user) throws BusinessException;

    /**
     * 用户登录（密码用明文）
     *
     * @param user user
     * @throws BusinessException BusinessException
     */
    void signIn(User user) throws BusinessException;
}
