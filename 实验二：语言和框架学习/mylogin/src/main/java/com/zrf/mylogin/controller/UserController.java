package com.zrf.mylogin.controller;

import com.zrf.mylogin.entity.User;
import com.zrf.mylogin.result.ResultError;
import com.zrf.mylogin.result.ResultVo;
import com.zrf.mylogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //指明该类下的所有方法都会返回json数据格式
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService; //注入service接口类

    @PostMapping("/login") //post请求
    public ResultVo login(User user) {
        //调用service接口，查找该用户是否存在
        if (userService.getByUNameAndPWord(user.getUsername(), user.getPassword()) != null) {
            //返回成功的信息
            return ResultVo.success(user);
        } else {
            //返回失败的信息
            return ResultVo.failure(ResultError.LOGIN_FAILURE);
        }
    }

}
