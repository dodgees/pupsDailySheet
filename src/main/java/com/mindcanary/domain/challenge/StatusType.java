package com.mindcanary.domain.challenge;

import com.mindcanary.exceptions.EnumerationException;

public enum StatusType {
	ASKED(1, "Asked"), ANSWERED(2, "Answered");

	private final long id;

	private final String name;

	private StatusType(long id, String name) {
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

}
