package main.java.dao;

import main.java.pojo.User;

public interface UserDao {

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean register(String username, String password);

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

}