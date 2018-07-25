package com.mindcanary.exceptions;

public class ServiceError {
	
	private String level;
	private String message;
	
	public ServiceError(String level, String message) {
		this.level = level;
		this.message = message;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
