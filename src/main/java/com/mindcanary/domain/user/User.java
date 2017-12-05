package com.mindcanary.domain.user;

public class User {

	private long id;

	private String uid;

	private String firstName;

	private String lastName;

	public User(String uid) {
		this.uid = uid;
	}

	public User(long id, String firstName, String lastName, String uid) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.uid = uid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
