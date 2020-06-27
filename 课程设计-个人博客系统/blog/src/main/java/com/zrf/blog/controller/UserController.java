package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.enums.StateEnums;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.UserService;
import com.zrf.blog.token.MyUsernamePasswordToken;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张润发
 * @date 2020/6/27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public Result<Object> login(@RequestBody User user) {
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new MyUsernamePasswordToken(user.getUsername(), user.getPassword(), StateEnums.USER.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        User u = (User) subject.getPrincipal();
        u.setPassword("");
        u.setDeleted(null);
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        returnMap.put("user", u);
        return new Result<>(returnMap);
    }

}
