package com.zrf.blog.service.impl;

import com.zrf.blog.exception.BlogException;
import com.zrf.blog.mapper.TypeMapper;
import com.zrf.blog.pojo.Type;
import com.zrf.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public List<Type> getTypeList() {
        return typeMapper.getTypeList();
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public Type getById(Integer id) {
        return typeMapper.getById(id);
    }
}
