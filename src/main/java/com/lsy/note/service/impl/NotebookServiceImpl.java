package com.lsy.note.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.note.dao.NotebookDao;
import com.lsy.note.dao.UserDao;
import com.lsy.note.enity.User;
import com.lsy.note.service.NotebookService;
import com.lsy.note.service.UserNotFoundException;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NotebookDao notebookDao;

	public List<Map<String, Object>> listNotebooks(String userId) throws UserNotFoundException {
		
		if (userId==null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("ID不能为空");
		}
		
		int n = userDao.countUserById(userId);
		if(n!=1){
			throw new UserNotFoundException("用户不存在");
		}
		
		return notebookDao.findNotebooksByUserId(userId);
	}

}
