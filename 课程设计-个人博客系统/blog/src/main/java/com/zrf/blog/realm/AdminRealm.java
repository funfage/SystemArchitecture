package com.zrf.blog.realm;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.enums.StateEnums;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.pojo.Admin;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.AdminService;
import com.zrf.blog.service.UserService;
import com.zrf.blog.token.MyUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理管理员的登录和授权逻辑
 *
 * @author 张润发
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        MyUsernamePasswordToken usernamePasswordToken = (MyUsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        int state = usernamePasswordToken.getState();
        if (state == StateEnums.ADMIN.getCode()) {
            Admin admin = adminService.getByUsername(username);
            if (admin == null) {
                // 用户不存在
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在！");
            }
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
        } else {
            User user = userService.getByUsername(username);
            if (user == null || user.getDeleted() == 1) {
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在！");
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }
    }
}
