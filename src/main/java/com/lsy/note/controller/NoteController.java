package com.lsy.note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsy.note.enity.Note;
import com.lsy.note.service.NoteService;
import com.lsy.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController{
	
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(String notebookId){
		
		List<Map<String,Object>> list=noteService.listNotes(notebookId);
		
		return new JsonResult(list);
	}
	
	@RequestMapping("/load.do")
	@ResponseBody
	public JsonResult load(String noteId){
		Note note = noteService.getNote(noteId);
		return new JsonResult(note);
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult add(String userId, String notebookId, String title) {
	    Note note = noteService.addNote(userId, notebookId, title);
	    return new JsonResult(note);
	}
	
	

}
