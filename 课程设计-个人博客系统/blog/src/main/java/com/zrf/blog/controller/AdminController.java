package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.enums.StateEnums;
import com.zrf.blog.pojo.Admin;
import com.zrf.blog.service.AdminService;
import com.zrf.blog.token.MyUsernamePasswordToken;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.ShiroUtils;
import com.zrf.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
        private AdminService adminService;

    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody Admin admin) {
        if (admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new MyUsernamePasswordToken(admin.getUsername(), admin.getPassword(), StateEnums.ADMIN.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @GetMapping("/info")
    public Result<Admin> getLoginInfo() {
        Admin admin = (Admin) ShiroUtils.getLoginUser();
        admin.setPassword("");
        return new Result<>(admin);
    }

    /**
     * 查询管理员
     * @return
     */
    @GetMapping("/getAdmin")
    public Result<Object> getAdmin() {
        Admin admin = adminService.getAdmin();
        return new Result<>(admin);
    }

    /**
     * 更新个人信息
     * @param admin
     * @return
     */
    @PutMapping("/updateInfo")
    public Result<Object> updateInfo(@RequestBody Admin admin) {
        if (admin.getId() == null) {
            return new Result<>(ResultEnum.PARAMS_NULL, "id");
        }
        adminService.updateInfo(admin);
        return new Result<>("更新成功！");
    }

}
