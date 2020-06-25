package com.zrf.blog.service;

import com.zrf.blog.pojo.Link;

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
}
