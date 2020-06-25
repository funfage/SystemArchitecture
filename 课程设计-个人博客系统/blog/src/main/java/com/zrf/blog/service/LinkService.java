package com.zrf.blog.service;

import com.zrf.blog.pojo.Link;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
public interface LinkService {

    /**
     * 添加友情链接
     * @param link
     */
    void save(Link link);

    /**
     * 修改
     * @param link
     */
    void update(Link link);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Link> getAll();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
