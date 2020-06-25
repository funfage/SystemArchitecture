package com.zrf.blog.service;

import com.zrf.blog.pojo.Admin;

/**
 * @author 张润发
 * @date 2020/6/25
 */
public interface AdminService {

    /**
     * 获取管理员信息
     * @return
     */
    Admin getAdmin();

    /**
     * 修改管理员信息
     * @param admin
     */
    void updateInfo(Admin admin);
}
