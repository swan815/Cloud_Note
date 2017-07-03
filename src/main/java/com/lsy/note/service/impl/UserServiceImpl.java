package com.lsy.note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.User;
import com.lsy.note.service.PasswordException;
import com.lsy.note.service.UserNotFoundException;
import com.lsy.note.service.UserService;

@Service("userService")//等同于@Component, 按照接口声明的ID
public class UserServiceImpl implements UserService{
	

	@Autowired //利用Spring提供的组件注入功能，将ID为userDao的Bean组件注入到变量userDao中
	private UserDao userDao;

	public User login(String name, String password) throws UserNotFoundException, PasswordException {
		if (name==null || name.trim().isEmpty()) {
			throw new UserNotFoundException("用户名不能为空");
		}
		if (password==null || password.trim().isEmpty()) {
			throw new PasswordException("密码不能为空");
		}
		User user = userDao.findUserByName(name);
		if (user==null) {
			throw new UserNotFoundException("查无此人");
			
		}
		if (user.getPassword().equals(password)) {
			return user;
		}
		throw new PasswordException("密码不对");
	}

}
