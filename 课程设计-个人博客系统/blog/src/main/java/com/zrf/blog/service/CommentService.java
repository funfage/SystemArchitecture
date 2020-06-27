package com.zrf.blog.service;

import com.zrf.blog.pojo.Comment;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/27
 */
public interface CommentService {

    /**
     * 添加
     * @param comment
     */
    void save(Comment comment);

    /**
     * 根据博客id查询
     * @param blogId
     * @return
     */
    List<Comment> getByBlog(String blogId);
}
