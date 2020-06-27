package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.enums.StateEnums;
import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.UserService;
import com.zrf.blog.token.MyUsernamePasswordToken;
import com.zrf.blog.utils.Page;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
     * 保存
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody User user) {
        userService.save(user);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @PutMapping(value = "/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody User user) {
        if (user.getVersion() == null) {
            return new Result<>(ResultEnum.PARAMS_NULL, "version");
        }
        userService.update(user);
        return new Result<>("修改成功！");
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @PutMapping(value = "/updateInfo")
    public Result<Object> updateInfo(@Validated({Update.class}) @RequestBody User user) {
        userService.updateInfo(user);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<User> get(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new Result<>(user);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/getByPage")
    public Result<Page<User>> getByPage(@RequestBody Page<User> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"sex", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = userService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PutMapping(value = "/resetPwd")
    public Result<Object> resetPwd(@RequestBody List<Integer> userIds) {
        if (userIds.size() <= 0) {
            return new Result<>(ResultEnum.ERROR.getCode(), "请选择用户！");
        }
        userService.resetPwd(userIds);
        return new Result<>("重置完毕！");
    }

    /**
     * 注册
     */
    @PostMapping(value = "/register")
    public Result<Object> register(@Validated({Insert.class}) @RequestBody User user) {
        userService.register(user);
        return new Result<>("注册成功！");
    }

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
