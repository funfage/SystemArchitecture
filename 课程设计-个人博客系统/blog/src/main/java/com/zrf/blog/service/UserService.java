package com.zrf.blog.service;

import com.zrf.blog.pojo.User;
import com.zrf.blog.utils.Page;

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

    /**
     * 添加
     * @param user
     */
    void save(User user);

    /**
     * 更新
     * @param user
     */
    void update(User user);

    /**
     * 修改个人信息
     * @param user
     */
    void updateInfo(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<User> getByPage(Page<User> page);
}
