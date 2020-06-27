package com.zrf.blog.service;

import com.zrf.blog.pojo.Blog;
import com.zrf.blog.pojo.BlogCollection;
import com.zrf.blog.utils.Page;
import com.zrf.blog.vo.BlogVo;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/25
 */
public interface BlogService {

    /**
     * 保存博客
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 修改博客
     * @param blog
     */
    void update(Blog blog);

    /**
     * 阅读
     * @param id
     * @return
     */
    BlogVo readById(String id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 查询时间轴
     * @return
     */
    List<BlogVo> getTimeLine();

    /**
     * 根据博客id查询点赞数
     * @param blogId
     * @return
     */
    int getGoodsCount(String blogId);

    /**
     * 收藏
     * @param blogCollection
     */
    void collectionByBlogId(BlogCollection blogCollection);

    /**
     * 查询收藏数
     * @param blogId
     * @return
     */
    int getCollectionCount(String blogId);

    /**
     * 分页查询收藏
     * @param page
     * @return
     */
    Page<BlogCollection> getCollectionByPage(Page<BlogCollection> page);

}
