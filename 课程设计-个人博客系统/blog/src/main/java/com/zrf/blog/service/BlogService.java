package com.zrf.blog.service;

import com.zrf.blog.pojo.Blog;
import com.zrf.blog.utils.Page;
import com.zrf.blog.vo.BlogVo;

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

    /**
     * 阅读
     * @param id
     * @return
     */
    BlogVo readById(String id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);
}
