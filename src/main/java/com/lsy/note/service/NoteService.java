package com.lsy.note.service;

import java.util.List;
import java.util.Map;

import com.lsy.note.enity.Note;

public interface NoteService {
	List<Map<String,Object>> listNotes(String notebookId) 
		throws NotebookNoteFoundException;
	
	Note getNote(String noteId) throws NoteNotFoundException;

}
