package com.mindcanary.exceptions;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorizationException() {
        super("YOU SHALL NOT PASS!");
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
