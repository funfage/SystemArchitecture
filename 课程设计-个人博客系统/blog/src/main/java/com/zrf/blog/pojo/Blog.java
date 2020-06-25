package com.zrf.blog.pojo;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 博客表实体类
 * </p>
 *
 * @author 张润发
 *
 */
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = -559415810554538278L;

    /**
     * 帖子id
     */
    @NotNull(message = "blogId不能为空！", groups = {Update.class})
    private String blogId;

    /**
     * 博客分类
     */
    @NotNull(message = "blogType不能为空", groups = {Insert.class, Update.class})
    private Integer blogType;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 封面
     */
    private String blogImage;

    /**
     * 帖子内容
     */
    private String blogContent;

    /**
     * 点赞数
     */
    private Integer blogGoods;

    /**
     * 阅读数
     */
    private Integer blogRead;

    /**
     * 收藏数
     */
    private Integer blogCollection;


    /**
     * 简介
     */
    private String blogRemark;

    /**
     * 评论数
     */
    private Integer blogComment;

    /**
     * 文章来源（爬虫时使用）
     */
    private String blogSource;

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
    @NotNull(message = "version不能为空！", groups = {Update.class})
    private Integer version;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

}
