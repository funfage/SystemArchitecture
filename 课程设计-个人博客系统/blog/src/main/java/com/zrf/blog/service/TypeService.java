package com.zrf.blog.service;

import com.zrf.blog.pojo.Type;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
public interface TypeService {

    /**
     * 分类添加
     * @param type
     */
    void save(Type type);

    /**
     * 后台查询所有
     * @return
     */
    List<Type> getAll();

    /**
     * 前台查询所有
     * @return
     */
    List<Type> getTypeList();

    /**
     * 分类修改
     * @param type
     */
    void update(Type type);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Type getById(Integer id);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);
}
