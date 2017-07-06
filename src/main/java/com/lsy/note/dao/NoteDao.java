package com.lsy.note.dao;

import java.util.List;
import java.util.Map;

import com.lsy.note.enity.Note;

public interface NoteDao {
	List<Map<String,Object>> findNotesByNotebookId(String notebookId);
	
	Note findNoteById(String noteId);
	
	int addNote(Note note);

}
