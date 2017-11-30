package com.mindcanary.domain.challenge;

import java.time.LocalDateTime;

import com.mindcanary.domain.user.User;

public class Challenge {

	private long id;

	private User toUser;

	private User fromUser;

	private LocalDateTime createdDateTime;

	private String title;

	private String category;

	private String description;

	private AnswerType answerType;
	
	public Challenge() {
	}

	public Challenge(long id, User toUser, User fromUser, LocalDateTime createdDateTime, String title, String category,
			String description, AnswerType answerType) {
		super();
		this.id = id;
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.createdDateTime = createdDateTime;
		this.title = title;
		this.category = category;
		this.description = description;
		this.answerType = answerType;
	}

	public Challenge(String title, String category) {
		super();
		this.title = title;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AnswerType getAnswerType() {
		return answerType;
	}

	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}

}
