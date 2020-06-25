package com.zrf.blog.controller;

import com.zrf.blog.group.Insert;
import com.zrf.blog.pojo.Type;
import com.zrf.blog.service.TypeService;
import com.zrf.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping("/type")
@Validated
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加type
     *
     * @param type
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody Type type) {
        typeService.save(type);
        return new Result<>("添加成功！");
    }

}
