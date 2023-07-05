package com.simple.simplepass.exceptions;

public class BearerTokenException extends RuntimeException {
    public BearerTokenException(String message) {
        super(message);
    }
}
