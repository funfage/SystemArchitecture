package com.zrf.mylogin.dao;

import com.zrf.mylogin.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);

}
