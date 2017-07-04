package com.lsy.note.service;

import java.util.List;
import java.util.Map;

public interface NotebookService {
	
	List<Map<String,Object>> listNotebooks(String userId)
		throws UserNotFoundException;
	

}
