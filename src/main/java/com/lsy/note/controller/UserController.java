package com.lsy.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsy.note.enity.User;
import com.lsy.note.service.UserService;
import com.lsy.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name,String password){
		User user = userService.login(name, password);
        return new JsonResult(user);
	}
	//@ExceptionHandler 注解方法在控制器方法出现异常时候执行
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleException(Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}
