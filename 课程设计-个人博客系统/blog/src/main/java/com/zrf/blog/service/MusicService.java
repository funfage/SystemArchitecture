package com.zrf.blog.service;

import com.zrf.blog.pojo.Music;
import com.zrf.blog.utils.Page;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/26
 */
public interface MusicService {

    /**
     * 添加
     * @param music
     */
    void save(Music music);

    /**
     * 修改
     * @param music
     */
    void update(Music music);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 禁用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Music> getByPage(Page<Music> page);

    /**
     * 前台查询
     * @return
     */
    List<Music> getList();
}
