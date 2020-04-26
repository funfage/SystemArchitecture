package com.zrf.mylogin;


import com.zrf.mylogin.dao.UserMapper;
import com.zrf.mylogin.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("111");
        //向数据库中插入一条用户信息
        int i = userMapper.insertUser(user);
        System.out.println(i);
    }

}
