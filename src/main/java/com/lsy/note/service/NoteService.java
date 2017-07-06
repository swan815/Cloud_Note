package com.lsy.note.service;

import java.util.List;
import java.util.Map;

import com.lsy.note.enity.Note;

public interface NoteService {
	List<Map<String,Object>> listNotes(String notebookId) 
		throws NotebookNotFoundException;
	
	Note getNote(String noteId) throws NoteNotFoundException;
	
	public Note addNote(String userId, String notebookId, String title)
	        throws UserNotFoundException,NotebookNotFoundException;

}
