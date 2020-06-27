package com.zrf.blog.dao;

import com.zrf.blog.pojo.CommentGoods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张润发
 */
@Repository
public interface CommentGoodsDao extends MongoRepository<CommentGoods, String> {

    /**
     * 根据用户id和评论id查询
     *
     * @param userId
     * @param commentId
     * @return
     */
    int countByUserIdAndCommentId(Integer userId, String commentId);

    /**
     * 根据评论id查询
     *
     * @param commentIds
     * @return
     */
    List<CommentGoods> findByCommentIdIn(List<String> commentIds);
}
