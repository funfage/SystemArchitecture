package com.zrf.blog.controller;

import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.About;
import com.zrf.blog.service.AboutService;
import com.zrf.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping("/about")
@Validated
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 保存
     *
     * @param about
     * @return
     */
    @PostMapping(value = "/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody About about) {
        aboutService.save(about);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param about
     * @return
     */
    @PutMapping(value = "/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody About about) {
        aboutService.update(about);
        return new Result<>("修改成功！");
    }

}
