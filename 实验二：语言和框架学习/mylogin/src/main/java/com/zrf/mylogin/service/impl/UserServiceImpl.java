package com.zrf.mylogin.service.impl;

import com.zrf.mylogin.dao.UserMapper;
import com.zrf.mylogin.entity.User;
import com.zrf.mylogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getByUNameAndPWord(String userName, String passoword) {
        return userMapper.selectUser(userName, passoword);
    }
}
