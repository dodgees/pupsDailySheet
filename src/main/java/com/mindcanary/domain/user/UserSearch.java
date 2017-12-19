package com.mindcanary.domain.user;

public class UserSearch {

    private String firebaseUuid;

    private String firstName;

    private String lastName;

    public UserSearch(String firebaseUuid) {
        this.firebaseUuid = firebaseUuid;
    }

    public String getFirebaseUuid() {
        return firebaseUuid;
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
}
