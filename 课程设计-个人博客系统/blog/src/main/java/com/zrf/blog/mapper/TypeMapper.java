package com.zrf.blog.mapper;

import com.zrf.blog.pojo.Type;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 帖子类型表Mapper
 * </p>
 *
 * @author 张润发
 *
 */
@Component
public interface TypeMapper {


    /**
     * 根据名称查询
     * @param typeName
     * @return
     */
    Type getByName(String typeName);


    /**
     * 插入
     * @param type
     */
    void insert(Type type);

    /**
     * 查询所有
     * @return
     */
    List<Type> getAll();

    /**
     * 查询分类列表
     * @return
     */
    List<Type> getTypeList();

    /**
     * 更新
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
     * 根据id删除(逻辑删除)
     * @param id
     */
    void deleteById(Integer id);
}
