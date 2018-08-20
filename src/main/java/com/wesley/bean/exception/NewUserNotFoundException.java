package com.wesley.bean.exception;

public class NewUserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NewUserNotFoundException() {
		super();
	}

	public NewUserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NewUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewUserNotFoundException(String message) {
		super(message);
	}

	public NewUserNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
