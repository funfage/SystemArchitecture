package com.zrf.blog.service;

import com.zrf.blog.pojo.Blog;

/**
 * @author 张润发
 * @date 2020/6/25
 */
public interface BlogService {

    /**
     * 保存博客
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 修改博客
     * @param blog
     */
    void update(Blog blog);
}
