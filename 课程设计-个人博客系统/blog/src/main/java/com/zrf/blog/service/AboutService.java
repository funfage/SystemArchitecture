package com.zrf.blog.service;

import com.zrf.blog.pojo.About;
import com.zrf.blog.utils.Page;

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

    /**
     * 根据id获取
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 阅读
     * @return
     */
    About read();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<About> getByPage(Page<About> page);
}
