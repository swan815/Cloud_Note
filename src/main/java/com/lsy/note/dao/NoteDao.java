package com.lsy.note.dao;

import java.util.List;
import java.util.Map;

public interface NoteDao {
	List<Map<String,Object>> findNotesByNotebookId(String notebookId);

}
