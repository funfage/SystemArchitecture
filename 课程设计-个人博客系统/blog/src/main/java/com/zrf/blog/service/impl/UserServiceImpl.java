package com.zrf.blog.service.impl;

import com.zrf.blog.mapper.UserMapper;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
