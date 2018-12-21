package main.java.dao.impl;

import main.java.dao.CommentDao;
import main.java.pojo.Comment;
import main.java.utils.Utils_c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xyLonely
 * @date 2018/12/21 13:46
 */
public class CommentDaoImpl implements CommentDao {

    @Override
    public boolean deleteComment(int comment_id) {
        return false;
    }

    @Override
    public boolean addComment(Comment comment) {
        return false;
    }

    @Override
    public List<Comment> getComment(int article_id) {
        Connection connection = Utils_c3p0.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM t_comment WHERE article_id=? ORDER BY TIME";
        List<Comment> list = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, article_id);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Comment cm = new Comment();
                cm.setId(resultSet.getInt("id"));
                cm.setArticle_id(resultSet.getInt("article_id"));
                cm.setNickname(resultSet.getString("nickname"));
                cm.setTime(resultSet.getString("time"));
                cm.setStar(resultSet.getInt("star"));
                cm.setContent(resultSet.getString("content"));
                cm.setDiss(resultSet.getInt("diss"));
                list.add(cm);
            }
            Utils_c3p0.close(resultSet,pStatement,connection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int star_diss(int id, int star_or_diss) {
        return 0;
    }
}
