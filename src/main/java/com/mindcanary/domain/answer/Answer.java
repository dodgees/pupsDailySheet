package com.mindcanary.domain.answer;

import java.util.Objects;

public class Answer {


	private long id;

	private String value;

	private boolean correct;

	private long challengeId;

	private String submittedByUuid;

	public Answer() {
	}

	public Answer(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public String getSubmittedByUuid() {
		return submittedByUuid;
	}

	public void setSubmittedByUuid(String submittedByUuid) {
		this.submittedByUuid = submittedByUuid;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Answer)) {
			return false;
		}

		Answer answer = (Answer) obj;

		return id == answer.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
