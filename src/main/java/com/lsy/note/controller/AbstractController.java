package com.lsy.note.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsy.note.util.JsonResult;

public class AbstractController {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult handleException(Exception e) {
		e.printStackTrace();
		return new JsonResult(e);
	}

}