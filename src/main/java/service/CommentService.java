package main.java.service;

import main.java.pojo.Comment;

import java.util.List;

/**
 * @author xyLonely
 * @date 2018/12/21 13:43
 */
public interface CommentService {

    //通过文章的id获取对应的所有评论
    List<Comment> loadComment(int id);
}
