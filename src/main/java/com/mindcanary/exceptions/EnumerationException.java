package com.mindcanary.exceptions;

public class EnumerationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnumerationException() {
		super("Failed to convert Enumeration");
	}

	public EnumerationException(String message, Class<RuntimeException> enumerationClass) {
		super(message + ":" + enumerationClass.getName());
	}

	public EnumerationException(long id, String className) {
		super("Failed to conver Enumeration for class: " + className + " | id: " + id);
	}

	public EnumerationException(String name, String className) {
		super("Failed to conver Enumeration for class: " + className + " | name: " + name);
	}

}
