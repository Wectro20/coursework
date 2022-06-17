package com.andrii.exceptions;

public class ConferenceNotFoundException extends RuntimeException {
    public ConferenceNotFoundException(String message) {
        super(message);
    }

    public ConferenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
