package com.zrf.blog.service.impl;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.mapper.UserMapper;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.UserService;
import com.zrf.blog.utils.Md5Utils;
import com.zrf.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/27
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public Page<User> getByPage(Page<User> page) {
        // 查询数据
        List<User> userList = userMapper.getByPage(page);
        page.setList(userList);
        // 查询总数
        int totalCount = userMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void resetPwd(List<Integer> userIds) {
        // JDK8新特性：集合的流式操作，这里使用到了Lambda表达式
        // 如果仅仅是遍历的话，增强for循环的效率比流式操作要高
        // 但是可读性没有流式操作高。
        // 当遍历时操作的业务逻辑较多时，流式操作的性能比普通的for循环要高
        List<User> userList = userMapper.getByIds(userIds);
        userList.forEach(e -> {
            e.setPassword(Md5Utils.toMD5("123456"));
            // 在for循环中修改数据 case when
            userMapper.update(e);
        });
    }

    @Override
    public void register(User user) {
        // 先根据用户名查询用户是否存在
        User u = userMapper.getByUsername(user.getUsername());
        // 如果存在，提示用户已存在
        if (u != null) {
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(), "用户已存在！");
        }
        // 如果不存在，插入数据
        userMapper.save(user);
    }


}
