package com.lsy.note.service;

public class UserExistsException extends RuntimeException {


	private static final long serialVersionUID = 4770383161836493959L;

	public UserExistsException() {
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
