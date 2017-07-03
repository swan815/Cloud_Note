package com.lsy.note.util;

import java.io.Serializable;

import com.lsy.note.service.UserNotFoundException;

/**
 * 封装JSON返回值，达到统一JSON返回值
 * @author apple
 *
 */
public class JsonResult implements Serializable {
	
	private static final long serialVersionUID = 8085386969271409944L;
	
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	private Object data;
	private String message;
	
	public JsonResult() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 返回正确的Json消息
	 * @param data
	 */
	public JsonResult(Object data) {
		state=SUCCESS;
		this.data=data;
		message="";
	}
	/**
	 * 返回错误的JSON消息
	 * @param e
	 */
	public JsonResult(Throwable e) {
		state=ERROR;
		this.data="";
		message=e.getMessage();
	}

	public JsonResult(int state, Throwable e) {
		this.state = state;
		this.data="";
		message=e.getMessage();
		
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
	}
}
