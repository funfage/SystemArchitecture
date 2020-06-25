package com.zrf.blog.mapper;

import com.zrf.blog.pojo.About;
import com.zrf.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author 张润发
 */
@Component
public interface AboutMapper {

    /**
     * 保存
     * @param about
     */
    void save(About about);


    /**
     * 根据id更新
     * @param about
     */
    void updateById(About about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 查询
     * @return
     */
    About getAbout();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     * @param about
     */
    void updateEnable(About about);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<About> getByPage(Page<About> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<About> page);
}
