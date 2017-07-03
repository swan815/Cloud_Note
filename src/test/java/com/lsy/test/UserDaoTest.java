package com.lsy.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.User;

public class UserDaoTest {
	ClassPathXmlApplicationContext ctx;
	UserDao dao;
	
	@Before
	public void initCtx(){
		 ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		 dao = ctx.getBean("userDao",UserDao.class);
	}
	
	@After
	public void destroy(){
		ctx.close();
	}
	
	
	@Test
	public void testFindUserByName(){
		
		User user = dao.findUserByName("demo");
		System.out.println(user);
	}


}
