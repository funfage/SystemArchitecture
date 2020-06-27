package com.zrf.blog.controller;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.pojo.Comment;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.CommentService;
import com.zrf.blog.utils.Result;
import com.zrf.blog.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/27
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 保存
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Comment comment) {
        User user = (User) ShiroUtils.getLoginUser();
        comment.setCommentUser(user.getUserId());
        if (StringUtils.isBlank(comment.getCommentBlog())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "博客id不能为空！");
        }
        commentService.save(comment);
        return new Result<>("评论成功！");
    }

    /**
     * 查询当前博客的评论
     *
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getByBlog/{blogId}", method = RequestMethod.GET)
    public Result<List<Comment>> getByBlog(@PathVariable String blogId) {
        List<Comment> comments = commentService.getByBlog(blogId);
        return new Result<>(comments);
    }

}
