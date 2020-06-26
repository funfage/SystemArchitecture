package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.Music;
import com.zrf.blog.service.MusicService;
import com.zrf.blog.utils.Page;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/26
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 保存
     *
     * @param music
     * @return
     */
    @PostMapping(value = "/save")
    public Result<Object> save(@RequestBody @Validated({Insert.class}) Music music) {
        musicService.save(music);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param music
     * @return
     */
    @PutMapping(value = "/update")
    public Result<Object> update(@RequestBody @Validated({Update.class}) Music music) {
        musicService.update(music);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<Music> get(@PathVariable Integer id) {
        Music music = musicService.getById(id);
        return new Result<>(music);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id) {
        musicService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/enable/{id}")
    public Result<Object> enable(@PathVariable Integer id) {
        musicService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/disable/{id}")
    public Result<Object> disable(@PathVariable Integer id) {
        musicService.disableById(id);
        return new Result<>("弃用成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/getByPage")
    public Result<Page<Music>> getByPage(@RequestBody Page<Music> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"artist", "created_time", "enabled"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = musicService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 前台查询
     *
     * @return
     */
    @GetMapping(value = "/getList")
    public Result<List<Music>> getList() {
        List<Music> musicList = musicService.getList();
        return new Result<>(musicList);
    }

}
