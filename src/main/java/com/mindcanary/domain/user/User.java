package com.mindcanary.domain.user;

public class User {

	private long id;

	private String uid;

	private String firstName;

	private String lastName;

	private String nickName;

	private String userName;

	private String email;

	private long challengeCoins;

	private String firebaseUuid;

	private String bio;

	public User(String firebaseUuid) {
		this.firebaseUuid = firebaseUuid;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getChallengeCoins() {
		return challengeCoins;
	}

	public void setChallengeCoins(long challengeCoins) {
		this.challengeCoins = challengeCoins;
	}

	public String getFirebaseUuid() {
		return firebaseUuid;
	}

	public void setFirebaseUuid(String firebaseUuid) {
		this.firebaseUuid = firebaseUuid;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
