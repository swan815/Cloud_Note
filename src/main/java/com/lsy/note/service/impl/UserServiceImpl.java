package com.lsy.note.service.impl;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.User;
import com.lsy.note.service.PasswordException;
import com.lsy.note.service.UserExistsException;
import com.lsy.note.service.UserNotFoundException;
import com.lsy.note.service.UserService;

@Service("userService")//等同于@Component, 按照接口声明的ID
public class UserServiceImpl implements UserService{
	

	@Autowired //利用Spring提供的组件注入功能，将ID为userDao的Bean组件注入到变量userDao中
	private UserDao userDao;
	
	@Value("#{config.salt}")
	private String salt;

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
		
		String pwd = DigestUtils.md5Hex(password+salt);
		
		if (user.getPassword().equals(pwd)) {
			return user;
		}
		throw new PasswordException("密码不对");
	}

	public User regist(String name, String nick, String password, String confirm)
			throws UserExistsException, PasswordException {
		
		if (name==null || name.trim().isEmpty()) {//计算机里的或无法交换顺序，先计算第一个表达式，
			//如果第一个表达式能确定整个表达式的结果，则之后不再执行
			throw new RuntimeException("用户名不能为空");
		}
		User one = userDao.findUserByName(name);
		if(one != null){
			throw new UserExistsException("用户已经注册");
		}
		if(password == null || password.trim().isEmpty()){
			throw new PasswordException();
		}
		
		if(!password.equals(confirm)){
			throw new PasswordException("确认密码不一致");
		}
		if (nick==null) {
			nick=name;
		}
		
		String id = UUID.randomUUID().toString();
		String pwd = DigestUtils.md5Hex(password+salt);
		String token="";
		User user = new User(id, name, pwd, token, nick);
		int n = userDao.addUser(user);
		if(n!=1){
			throw new RuntimeException("添加失败");
		}
		return user;
	}

}
