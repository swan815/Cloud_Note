package com.lsy.note.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lsy.note.enity.User;
import com.lsy.note.service.PasswordException;
import com.lsy.note.service.UserNotFoundException;
import com.lsy.note.service.UserService;
import com.lsy.note.util.JsonResult;

@Controller
@RequestMapping("/user")//spring mvc组件映射了url与控制器之间的关系
public class UserController extends AbstractController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name,String password){
		User user = userService.login(name, password);
        return new JsonResult(user);
	}
	
	
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public JsonResult handleUserNotFoundException(UserNotFoundException e){
		e.printStackTrace();
		return new JsonResult(2,e);//state=2
	}
	
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult handlePasswordException(PasswordException e){
		e.printStackTrace();
		return new JsonResult(3,e);//state=3
	}
	

	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name, String nick, String password,String confirm){
		User user = userService.regist(name, nick, password, confirm);
		return new JsonResult(user);
	}
	
	@Value("#{config.uploadPath}")
	private String uploadPath;
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public JsonResult upload(MultipartFile userfile1, MultipartFile userfile2) throws IllegalStateException, IOException{
		//MultipartFile 对象中包含所有的上传信息
		
		//获取文件名
		String name1 = userfile1.getOriginalFilename();
		String name2 = userfile2.getOriginalFilename();
		
		System.out.println(name1);
		System.out.println(name2);
		
		File dir = new File(uploadPath);
		dir.mkdir();//创建文件夹
		
		File file1=new File(dir,name1);
		File file2=new File(dir,name2);
		
		//保存文件
		userfile1.transferTo(file1);
		userfile2.transferTo(file2);
		
		return new JsonResult((Object)"OK");
		
		
		
	}
	
	
	
}
