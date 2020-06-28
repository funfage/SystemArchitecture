package com.zrf.blog.service.impl;

import com.zrf.blog.dao.CommentDao;
import com.zrf.blog.dao.CommentGoodsDao;
import com.zrf.blog.mapper.BlogMapper;
import com.zrf.blog.mapper.UserMapper;
import com.zrf.blog.pojo.Blog;
import com.zrf.blog.pojo.Comment;
import com.zrf.blog.pojo.CommentGoods;
import com.zrf.blog.pojo.User;
import com.zrf.blog.service.CommentService;
import com.zrf.blog.utils.IdWorker;
import com.zrf.blog.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张润发
 * @date 2020/6/27
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentGoodsDao commentGoodsDao;

    @Override
    public void save(Comment comment) {
        // 评论数+1
        // 查询博客
        Blog blog = blogMapper.getById(comment.getCommentBlog());
        blog.setBlogComment(blog.getBlogComment() + 1);
        blogMapper.update(blog);
        comment.setBlog(blog);
        comment.setId(idWorker.nextId() + "");
        comment.setCommentGood(0);
        comment.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 查询用户
        User user = userMapper.getById(comment.getCommentUser());
        comment.setUser(user);
        commentDao.save(comment);
    }

    @Override
    public List<Comment> getByBlog(String blogId) {
        // 查询博客评论
        List<Comment> commentList = commentDao.findByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(blogId);
        // 查询点赞情况
        // 取出评论id
        List<String> commentIds = commentList.stream().map(Comment::getId).collect(Collectors.toList());
        List<CommentGoods> commentGoodsList = commentGoodsDao.findByCommentIdIn(commentIds);
        // 遍历去封装评论情况
        commentList.forEach(e -> {
            commentGoodsList.forEach(good -> {
                if (e.getId().equals(good.getCommentId())) {
                    // 匹配到了评论记录
                    e.setCommentFlag(true);
                }
            });
        });
        return commentList;
    }

    @Override
    public void deleteById(String id) {
        commentDao.deleteById(id);
    }

    @Override
    public void goodByCommentIdAndUser(CommentGoods commentGoods) {
        User user = (User) ShiroUtils.getLoginUser();
        commentGoods.setUserId(user.getUserId());
        // 取出评论id，点赞数+1
        String commentId = commentGoods.getCommentId();
        Comment comment = commentDao.findById(commentId).get();
        comment.setCommentGood(comment.getCommentGood() + 1);
        commentDao.save(comment);
        try {
            commentGoods.setId(idWorker.nextId() + "");
            commentGoodsDao.save(commentGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
