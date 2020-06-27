package com.zrf.blog.pojo;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户表实体类
 * </p>
 *
 * @author 张润发
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -131715810554534737L;

    /**
     * 用户id
     */
    @NotNull(message = "userId不能为空！", groups = {Update.class})
    private Integer userId;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空！", groups = {Insert.class, Update.class})
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空！", groups = {Insert.class})
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别。1男2女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String header;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否删除。0否1是
     */
    private Integer deleted;

}
