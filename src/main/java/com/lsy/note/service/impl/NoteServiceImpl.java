package com.lsy.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lsy.note.dao.NoteDao;
import com.lsy.note.dao.NotebookDao;
import com.lsy.note.enity.Note;
import com.lsy.note.service.NoteNotFoundException;
import com.lsy.note.service.NoteService;
import com.lsy.note.service.NotebookNoteFoundException;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Resource
	private NoteDao noteDao;
	
	@Resource
	private NotebookDao notebookDao;
	

	public List<Map<String, Object>> listNotes(String notebookId) 
			throws NotebookNoteFoundException {
		if(notebookId == null || notebookId.trim().isEmpty()){
			throw new NotebookNoteFoundException("ID为空");
		}
		int n = notebookDao.countNotebookById(notebookId);
		
		if (n!=1) {
			throw new NotebookNoteFoundException("没有笔记本");
		}
		return noteDao.findNotesByNotebookId(notebookId);
	}


	public Note getNote(String noteId) 
			throws NoteNotFoundException {
		if (noteId==null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("ID空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("ID错误");
		}
		return note;
	}

}
