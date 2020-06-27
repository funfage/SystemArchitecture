package com.zrf.blog.service;

import com.zrf.blog.pojo.User;

/**
 * @author 张润发
 * @date 2020/6/27
 */
public interface UserService {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User getByUsername(String username);
}
