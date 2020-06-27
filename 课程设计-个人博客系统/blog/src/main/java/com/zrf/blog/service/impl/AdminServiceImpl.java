package com.zrf.blog.service.impl;

import com.zrf.blog.mapper.AdminMapper;
import com.zrf.blog.pojo.Admin;
import com.zrf.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin() {
        return adminMapper.getAdmin();
    }

    @Override
    public void updateInfo(Admin admin) {
        adminMapper.update(admin);
    }

    @Override
    public void updatePassword(Admin admin) {
        Admin oldAdmin = adminMapper.getAdmin();
        oldAdmin.setPassword(admin.getPassword());
        adminMapper.update(oldAdmin);
    }

    @Override
    public Admin getByUsername(String username) {
        return adminMapper.getByUsername(username);
    }


}
