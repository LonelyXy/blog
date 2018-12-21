package main.java.dao.impl;

import main.java.dao.UserDao;
import main.java.pojo.User;
import main.java.utils.Utils_c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author xyLonely
 * @date 2018/12/19 14:41
 */
public class UserDaoImpl implements UserDao {


    @Override
    public boolean register(String username, String password) {
        return false;
    }

    @Override
    public User login(String username, String password) {
        Connection connection = Utils_c3p0.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            pStatement = connection.prepareStatement("select * from t_user where user_name=? and user_password=?");
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_password(resultSet.getString("user_password"));
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Utils_c3p0.close(resultSet, pStatement, connection);
        }
    }
}
