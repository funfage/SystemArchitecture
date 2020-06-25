package com.zrf.blog.service;

import com.zrf.blog.pojo.About;

/**
 * @author 张润发
 * @date 2020/6/25
 */
public interface AboutService {

    /**
     * 添加
     * @param about
     */
    void save(About about);

    /**
     * 修改
     * @param about
     */
    void update(About about);
}
