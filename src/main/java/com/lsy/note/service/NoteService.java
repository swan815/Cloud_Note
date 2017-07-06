package com.lsy.note.service;

import java.util.List;
import java.util.Map;

public interface NoteService {
	List<Map<String,Object>> listNotes(String notebookId) 
		throws NotebookNoteFoundException;

}
