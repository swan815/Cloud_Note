package com.lsy.note.dao;

import com.lsy.note.enity.User;

public interface UserDao {
	
	/**
	 * 根据用户名查询用户信息
	 * @param name
	 * @return用户对象，代表用户信息
	 */
	User findUserByName(String name);
	
	int addUser(User user);

	int countUserById(String userId);

}
