package com.mindcanary.domain.challenge;

import com.mindcanary.exceptions.EnumerationException;

public enum StatusType {
	ASKED(1, "Asked"), ANSWERED_CORRECT(2, "Correct"), ANSWERED_INCORRECT(3, "Incorrect");

	private final long id;

	private final String name;

	StatusType(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static StatusType fromName(String name) {
		for (StatusType type : StatusType.values()) {
			if (type.name.equalsIgnoreCase(name)) {
				return type;
			}
		}
		throw new EnumerationException(name, StatusType.class.toString());
	}

	public static StatusType fromId(long id) {
		for (StatusType type : StatusType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		throw new EnumerationException(id, StatusType.class.toString());
	}

	public static StatusType fromAnswered(boolean correct) {
		if (correct) {
			return StatusType.ANSWERED_CORRECT;
		} else {
			return StatusType.ANSWERED_INCORRECT;
		}
	}

}
