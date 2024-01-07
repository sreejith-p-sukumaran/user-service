package com.example.user.exception;


public class ErrorDetails {
    private final String message;

    public ErrorDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
