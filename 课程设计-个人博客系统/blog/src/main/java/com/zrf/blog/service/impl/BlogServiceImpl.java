package com.zrf.blog.service.impl;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.mapper.BlogMapper;
import com.zrf.blog.mapper.TypeMapper;
import com.zrf.blog.pojo.Blog;
import com.zrf.blog.pojo.Type;
import com.zrf.blog.service.BlogService;
import com.zrf.blog.utils.IdWorker;
import com.zrf.blog.utils.Page;
import com.zrf.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private TypeMapper typeMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Blog blog) {
        blog.setBlogId(idWorker.nextId() + "");
        blogMapper.save(blog);
        //取出分类，当前分类博客数+1
        Integer blogType = blog.getBlogType();
        Type type = typeMapper.getById(blogType);
        type.setTypeBlogCount(type.getTypeBlogCount() + 1);
        typeMapper.update(type);
    }

    @Override
    public Blog getById(String id) {
        return blogMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog blog) {
        // 修改之前先进行查询
        Blog oldBlog = blogMapper.getById(blog.getBlogId());
        if (oldBlog == null) {
            log.error("oldBlog为null！");
            throw new BlogException(ResultEnum.ERROR.getCode(), "修改失败！");
        }
        blogMapper.update(blog);
        // 判断分类有没有被修改，如果被修改了，旧的分类博客数-1，新的分类博客数+1
        Integer oldTypeId = oldBlog.getBlogType();
        Integer nowTypeId = blog.getBlogType();
        if (nowTypeId != null && !oldTypeId.equals(nowTypeId)) {
            //旧的分类博客数-1
            Type oldType = typeMapper.getById(oldTypeId);
            oldType.setTypeBlogCount(oldType.getTypeBlogCount() - 1);
            typeMapper.update(oldType);
            //新的分类博客数+1
            Type nowType = typeMapper.getById(nowTypeId);
            nowType.setTypeBlogCount(nowType.getTypeBlogCount() + 1);
            typeMapper.update(nowType);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readById(String id) {
        Blog blog = blogMapper.getById(id);
        // 阅读，需要更新阅读数
        blog.setBlogRead(blog.getBlogRead() + 1);
        blogMapper.update(blog);
        // 将blog转为blogVo
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        // 查询分类
        Type type = typeMapper.getById(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    @Override
    public void deleteById(String id) {
        blogMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        // 查询数据
        List<BlogVo> blogVoList = blogMapper.getByPage(page);
        page.setList(blogVoList);
        // 查询总数
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
