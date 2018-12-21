package main.java.service;

import main.java.pojo.User;

/**
 * @author xyLonely
 * @date 2018/12/19 14:30
 */
public interface UserService {
    User login(String username,String password);
}
