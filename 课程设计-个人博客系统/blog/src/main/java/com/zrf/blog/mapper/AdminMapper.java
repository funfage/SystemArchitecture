package com.zrf.blog.mapper;

import com.zrf.blog.pojo.Admin;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author 在转让费
 */
@Component
public interface AdminMapper {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin getByUsername(String username);

    /**
     * 查询管理员
     * @return
     */
    Admin getAdmin();

    /**
     * 更新
     * @param admin
     */
    void update(Admin admin);
}
