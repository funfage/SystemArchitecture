package com.zrf.blog.service.impl;

import com.zrf.blog.mapper.LinkMapper;
import com.zrf.blog.pojo.Link;
import com.zrf.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public void save(Link link) {
        linkMapper.save(link);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    @Override
    public List<Link> getAll() {
        return linkMapper.getAll();
    }

    @Override
    public void deleteById(Integer id) {
        linkMapper.deleteById(id);
    }


}
