package com.lsy.test;

import org.junit.Before;
import org.junit.Test;

import com.lsy.note.enity.User;
import com.lsy.note.service.UserService;

public class UserServiceTest extends BaseTest {
	
	 UserService service; 
	
	 @Before
	 public void initService(){
		 service = ctx.getBean("userService",UserService.class);
	 }
	
	@Test
	public void testLogin(){
		String name="demo";
		String password="123456";
		User user = service.login(name, password);
		System.out.println(user);
	}
	
	@Test
	public void testRegist(){
		System.out.println(service.regist("LaoWang", "LaoWang", "123456", "123456"));
		
		
	}
	
	
}
