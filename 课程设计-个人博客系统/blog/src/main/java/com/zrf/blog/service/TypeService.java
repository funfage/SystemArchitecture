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
}
