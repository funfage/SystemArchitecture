package com.zrf.blog.controller;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.Link;
import com.zrf.blog.service.LinkService;
import com.zrf.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping
@Validated
public class LinkController {

    @Autowired
    private LinkService linkService;


    /**
     * 添加
     * @param link
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody Link link) {
        linkService.save(link);
        return new Result<>("添加成功！");
    }

    /**
     * 修改
     * @param link
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody Link link) {
        linkService.update(link);
        return new Result<>("修改成功！");
    }

}
