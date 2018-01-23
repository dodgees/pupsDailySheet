package com.mindcanary.exceptions;

public class ChallengeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChallengeException() {
        super("There was an exception with a challenge.");
    }

    public ChallengeException(String message) {
        super(message);
    }

}
