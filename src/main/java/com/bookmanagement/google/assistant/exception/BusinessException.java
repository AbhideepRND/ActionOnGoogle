package com.bookmanagement.google.assistant.exception;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
