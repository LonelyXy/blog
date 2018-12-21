package main.java.service.impl;

import main.java.dao.CommentDao;
import main.java.dao.impl.CommentDaoImpl;
import main.java.pojo.Comment;
import main.java.service.CommentService;

import java.util.List;

/**
 * @author xyLonely
 * @date 2018/12/21 13:44
 */
public class CommentServiceImpl implements CommentService {

    CommentDao commentDao = new CommentDaoImpl();

    @Override
    public List<Comment> loadComment(int article_id) {
        return commentDao.getComment(article_id);
    }
}
