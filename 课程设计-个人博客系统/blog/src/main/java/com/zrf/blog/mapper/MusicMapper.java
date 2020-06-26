package com.zrf.blog.mapper;

import com.zrf.blog.pojo.Music;
import com.zrf.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表Mapper
 * </p>
 *
 * @author 张润发
 *
 */
@Component
public interface MusicMapper {

    /**
     * 保存
     * @param music
     */
    void save(Music music);

    /**
     * 更新
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
     * 分页查询
     * @param page
     * @return
     */
    List<Music> getByPage(Page<Music> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<Music> page);

    /**
     * 前台查询
     * @return
     */
    List<Music> getList();
}
