package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.group.Insert;
import com.zrf.blog.group.Update;
import com.zrf.blog.pojo.Blog;
import com.zrf.blog.service.BlogService;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author 张润发
 * @date 2020/6/25
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 保存
     * @param blog
     *
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody Blog blog) {
        blogService.save(blog);
        return new Result<>("添加成功！");
    }

    /**
     * 根据id查询
     */
    @GetMapping("/get/{id}")
    public Result<Blog> get(@PathVariable @NotEmpty(message = "id不能为空！") String id) {
        if (StringUtils.isBlank(id)) {
            return new Result<>(ResultEnum.PARAMS_NULL, "blogId");
        }
        Blog blog = blogService.getById(id);
        return new Result<>(blog);
    }

    /**
     * 更新
     *
     * @param blog
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody Blog blog) {
        blogService.update(blog);
        return new Result<>("更新成功！");
    }

}
