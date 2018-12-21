package main.java.service.impl;

import main.java.dao.UserDao;
import main.java.dao.impl.UserDaoImpl;
import main.java.pojo.User;
import main.java.service.UserService;

/**
 * @author xyLonely
 * @date 2018/12/19 14:30
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }
}
