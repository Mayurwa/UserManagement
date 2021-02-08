package com.example.demo.exception;

public class UserAlreadyPresentException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>UserAlreadyPresentException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public UserAlreadyPresentException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UserAlreadyPresentException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public UserAlreadyPresentException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
