package com.lsy.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lsy.note.service.NoteService;

public class NoteServiceTest extends BaseTest {
	
	NoteService service;
	
	@Before
	public void initService(){
		service = ctx.getBean("noteService",NoteService.class);
	}
	
	@Test
	public void testListNotes(){
		String id="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String,Object>> list = service.listNotes(id); 
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

}
