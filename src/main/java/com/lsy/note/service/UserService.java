package com.lsy.note.service;

import com.lsy.note.enity.User;

public interface UserService {
	
	/**
	 * 登陆功能
	 * @param name
	 * @param password
	 * @return 登陆成功返回用户信息
	 * @throws UserNotFoundException 用户不存在
	 * @throws PasswordException 密码错误
	 */
	
	public User login(String name,String password) 
			throws UserNotFoundException,PasswordException;
	
	public User regist(String name,String nick,String password,String confirm)
			throws UserExistsException,PasswordException;

}
