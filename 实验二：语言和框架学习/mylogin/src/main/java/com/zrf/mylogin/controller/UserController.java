package com.zrf.mylogin.controller;

import com.zrf.mylogin.entity.User;
import com.zrf.mylogin.result.ResultError;
import com.zrf.mylogin.result.ResultVo;
import com.zrf.mylogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVo login(User user) {
        if (userService.getByUNameAndPWord(user.getUsername(), user.getPassword()) != null) {
            return ResultVo.success(user);
        } else {
            return ResultVo.failure(ResultError.LOGIN_FAILURE);
        }
    }

}
