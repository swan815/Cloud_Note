package com.lsy.test;

import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import com.lsy.note.dao.NoteDao;
import com.lsy.note.enity.Note;

public class NoteDaoTest extends BaseTest {
	
	NoteDao dao;
	
	@Before
	public void initDao(){
		dao=ctx.getBean("noteDao", NoteDao.class);
	}
	
	@Test
	public void testFindNotesByNotebookId(){
		String id="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String,Object>> list = dao.findNotesByNotebookId(id);
		
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void testFindNoteById(){
		String noteId="019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		Note note = dao.findNoteById(noteId);
		System.out.println(note);
	}

}
