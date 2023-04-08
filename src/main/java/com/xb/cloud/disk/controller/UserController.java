package com.xb.cloud.disk.controller;

import com.xb.cloud.disk.constant.SysConstants;
import com.xb.cloud.disk.entity.User;
import com.xb.cloud.disk.service.UserService;
import com.xb.cloud.disk.vo.ApiResponse;
import com.xb.cloud.disk.vo.user.UserSignInVO;
import com.xb.cloud.disk.vo.user.UserSignUpVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jack
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/signUp")
    public ApiResponse<Void> signUp(@RequestBody UserSignUpVO vo) {
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        userService.signUp(user);
        return ApiResponse.ok(null);
    }

    @PostMapping("signIn")
    public ApiResponse<Void> signIn(@RequestBody UserSignInVO vo, HttpServletRequest request) {
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setPassword(vo.getPassword());
        User userInfo = userService.signIn(user);
        // 登录成功，保存用户信息在会话里
        request.getSession().setAttribute(SysConstants.USER_CONTEXT_ATTR_NAME, userInfo);
        return ApiResponse.ok(null);
    }
}
