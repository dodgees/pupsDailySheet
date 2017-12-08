package com.mindcanary.domain.answer;

import com.mindcanary.exceptions.EnumerationException;

public enum AnswerType {
	ESSAY(1, "Essay"), MULTIPLE_CHOICE(2, "Multiple Choice");
	
	private final long id;
	
	private final String name;
	
	private AnswerType(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static AnswerType fromName(String name) {
		for (AnswerType type : AnswerType.values()) {
			if (type.name.equalsIgnoreCase(name)) {
				return type;
			}
		}
		throw new EnumerationException(name, AnswerType.class.toString());
	}

	public static AnswerType fromId(long id) {
		for (AnswerType type : AnswerType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		throw new EnumerationException(id, AnswerType.class.toString());
	}	

}
