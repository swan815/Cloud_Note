package com.lsy.note.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lsy.note.dao.NoteDao;
import com.lsy.note.dao.NotebookDao;
import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.Note;
import com.lsy.note.enity.User;
import com.lsy.note.service.NoteNotFoundException;
import com.lsy.note.service.NoteService;
import com.lsy.note.service.NotebookNotFoundException;
import com.lsy.note.service.UserNotFoundException;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private NoteDao noteDao;
	
	@Resource
	private NotebookDao notebookDao;
	

	public List<Map<String, Object>> listNotes(String notebookId) 
			throws NotebookNotFoundException {
		if(notebookId == null || notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("ID为空");
		}
		int n = notebookDao.countNotebookById(notebookId);
		
		if (n!=1) {
			throw new NotebookNotFoundException("没有笔记本");
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


	public Note addNote(String userId, String notebookId, String title)
		throws UserNotFoundException, NotebookNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
	        throw new UserNotFoundException("ID空");
	    }
	    User user=userDao.findUserById(userId);
	    if(user==null){
	        throw new UserNotFoundException("木有人");
	    }
	    if(notebookId==null||notebookId.trim().isEmpty()){
	        throw new NotebookNotFoundException("ID空");
	    }
	    int n=notebookDao.countNotebookById(notebookId);
	    if(n!=1){
	        throw new NotebookNotFoundException("没有笔记本");
	    }
	    if(title==null || title.trim().isEmpty()){
	        title="葵花宝典";
	    }
	    String id = UUID.randomUUID().toString();
	    String statusId = "0";
	    String typeId = "0";
	    String body = "";
	    long time=System.currentTimeMillis();
	    Note note = new Note(id, notebookId,
	        userId, statusId, typeId, title, 
	        body, time, time);
	    n = noteDao.addNote(note);
	    if(n!=1){
	        throw new NoteNotFoundException("保存失败");
	    }
	    return note;
		    }

}
