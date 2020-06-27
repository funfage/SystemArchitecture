package com.zrf.blog.service.impl;

import com.zrf.blog.dao.CommentDao;
import com.zrf.blog.service.CommentService;
import com.zrf.blog.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
