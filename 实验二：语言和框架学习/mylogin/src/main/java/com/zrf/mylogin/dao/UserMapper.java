package com.zrf.mylogin.dao;

import com.zrf.mylogin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User selectUser(@Param("username") String username, @Param("password") String password);

}
