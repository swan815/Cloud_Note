package com.lsy.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsy.note.enity.User;
import com.lsy.note.service.UserService;

public class UserServiceTest {
	ClassPathXmlApplicationContext ctx;
	UserService service;
	
	@Before
	public void initCtx(){
		 ctx = new ClassPathXmlApplicationContext(
				 "conf/spring-mybatis.xml",
				 "conf/spring-service.xml");
		 service = ctx.getBean("userService",UserService.class);
	}
	
	@After
	public void destroy(){
		ctx.close();
	}
	
	@Test
	public void testLogin(){
		String name="demo";
		String password="123456";
		User user = service.login(name, password);
		System.out.println(user);
	}
	
	
}
