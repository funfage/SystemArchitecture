package com.zrf.blog.service.impl;

import com.zrf.blog.mapper.MusicMapper;
import com.zrf.blog.pojo.Music;
import com.zrf.blog.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张润发
 * @date 2020/6/26
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }


}
