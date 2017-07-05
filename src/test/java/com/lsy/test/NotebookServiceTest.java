package com.lsy.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lsy.note.service.NotebookService;

public class NotebookServiceTest extends BaseTest {
	
	NotebookService service;
	
	@Before
	public void initService(){
		service=ctx.getBean("notebookService", NotebookService.class);
	}
	
	@Test
	public void testListNoteBooks(){
		String userId="524f7440-7283-4b2d-8af5-4a67570e892e";
		List<Map<String,Object>> list = service.listNotebooks(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
	}

}
