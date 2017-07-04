package com.lsy.test;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.User;

public class UserDaoTest{
	UserDao dao;
	
	ClassPathXmlApplicationContext ctx;
	UserDao useDao;
	
	@Before
	public void initCtx() {
		 ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		 dao = ctx.getBean("userDao",UserDao.class);
	}

	@After
	public void destroy() {
		ctx.close();
	}
	
	@Test
	public void testFindUserByName(){
		
		User user = dao.findUserByName("demo");
		System.out.println(user);
	}
	
	@Test
	public void testAddUser(){
		String id = UUID.randomUUID().toString();
		String name="Jerry";
		String password="123456";
		String token="";
		String nick="Mice";
		
		String salt="Day Day Up!";
		
		String pwd= DigestUtils.md5Hex(password+salt);
		
		User user = new User(id, name, pwd, token, nick);
		
		int n = dao.addUser(user);
		System.out.println(n);
	}


}
