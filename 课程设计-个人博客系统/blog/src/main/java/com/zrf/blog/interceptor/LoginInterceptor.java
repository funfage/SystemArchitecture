package com.zrf.blog.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.ShiroUtils;
import com.zrf.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张润发
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static List<Integer> errorCodeList = Arrays.asList(401, 402, 404, 405);

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
        //判断http请求是否出错
        if (errorCodeList.contains(response.getStatus())) {
            response.setContentType("application/json; charset=utf-8");
            log.error("http错误,错误码为：{}", response.getStatus());
            log.error("请求的url为：{}", request.getRequestURL());
            response.getWriter().write(JSONObject.toJSONString(new Result<>(ResultEnum.REQUEST_ERROR.getCode(),
                    "请求错误码为：" + response.getStatus())));
            return false;
        }
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
