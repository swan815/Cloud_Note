package com.lsy.note.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsy.note.service.NotebookService;
import com.lsy.note.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController extends AbstractController {

	@Autowired
	private NotebookService notebookService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(String userId){
		List<Map<String,Object>> list = notebookService.listNotebooks(userId);
		return new JsonResult(list);
		
	}
	
}
