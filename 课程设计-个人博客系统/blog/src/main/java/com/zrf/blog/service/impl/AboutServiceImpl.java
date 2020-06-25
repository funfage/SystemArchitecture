package com.zrf.blog.service.impl;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.mapper.AboutMapper;
import com.zrf.blog.pojo.About;
import com.zrf.blog.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutMapper aboutMapper;

    @Override
    public void save(About about) {
        About oldAbout = aboutMapper.getAbout();
        if (oldAbout != null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "只能添加一个”关于“");
        }
        aboutMapper.save(about);
    }

    @Override
    public void update(About about) {
        aboutMapper.updateById(about);
    }

}
