package com.zrf.blog.controller;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.Music;
import com.zrf.blog.service.MusicService;
import com.zrf.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

}
