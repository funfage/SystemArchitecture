package com.zrf.blog.interceptor;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.utils.ShiroUtils;
import com.zrf.blog.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张润发
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 放行的白名单
     */
    private static String[] whiteList = {
            "/admin/login",
            "/user/login",
            "/user/register",
            "/link/list",
            "/music/getList",
            "/about/read",
            "/type/getList",
            "/blog/recomRead",
            "/blog/read",
            "/blog/getTimeLine",
            "/blog/getByPage",
            "/comment/getByBlog",
            "/admin/getAdmin"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        // 获取token
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            // token不为空，获取当前登录用户
            Object loginUser = ShiroUtils.getLoginUser();
            if (loginUser != null) {
                // 说明token有效
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 判断url是否在白名单内
     *
     * @param s
     * @return
     */
    private boolean containsWhiteList(String s) {
        for (String url : whiteList) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }

}
