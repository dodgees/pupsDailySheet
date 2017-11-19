package com.mindcanary.exceptions;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super("Authentication failed.");
	}

	public AuthenticationException(String message) {
		super(message);
	}

}
