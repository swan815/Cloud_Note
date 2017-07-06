package com.lsy.note.service;

public class NoteNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -2108299586497464182L;

	public NoteNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
