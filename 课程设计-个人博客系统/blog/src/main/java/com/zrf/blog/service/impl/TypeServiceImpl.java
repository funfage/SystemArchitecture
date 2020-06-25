package com.zrf.blog.service.impl;

import com.zrf.blog.exception.BlogException;
import com.zrf.blog.mapper.TypeMapper;
import com.zrf.blog.pojo.Type;
import com.zrf.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        Type t = typeMapper.getByName(type.getTypeName());
        if (t != null) {
            throw new BlogException("该分类已经存在");
        }
        typeMapper.insert(type);
    }
}
