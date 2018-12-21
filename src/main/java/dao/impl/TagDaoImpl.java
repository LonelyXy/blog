package main.java.dao.impl;

import main.java.dao.TagDao;
import main.java.pojo.Tag;
import main.java.utils.Utils_c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xyLonely
 * @date 2018/12/20 9:46
 */
public class TagDaoImpl implements TagDao {


    @Override
    public boolean addTag(int id, String tag) {
        return false;
    }

    @Override
    public boolean deleteTag(int id, String tag) {
        return false;
    }

    //获取所有的重复标签
    @Override
    public List<Tag> getAllTag() {
        String sql = " select distinct(tag) from t_tag";
        Connection connection = Utils_c3p0.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        List<Tag> list = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setTag(resultSet.getString("tag"));
                list.add(tag);
            }
            Utils_c3p0.close(resultSet, pStatement, connection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateTag(String old_tag, String new_tag) {
        return false;
    }

    //通过某一属性获取对应的Tag列表
    @Override
    public List<Tag> getTagByColumn(String column, String value) {
        Connection connection = Utils_c3p0.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from t_tag where " + column + "=?";
        List<Tag> tags = new ArrayList<>();
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, value);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag(resultSet.getInt("id"), resultSet.getString("tag"));
                tags.add(tag);
            }
            Utils_c3p0.close(resultSet, pStatement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
}
