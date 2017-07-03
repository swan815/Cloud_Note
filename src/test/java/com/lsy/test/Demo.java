package com.lsy.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.User;

public class Demo {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao = ctx.getBean("userDao",UserDao.class);
		User user = dao.findUserByName("demo");
		System.out.println(user);
		
	}

}
