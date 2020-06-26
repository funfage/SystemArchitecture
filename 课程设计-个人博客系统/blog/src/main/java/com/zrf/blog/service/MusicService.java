package com.zrf.blog.service;

import com.zrf.blog.pojo.Music;

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
}
