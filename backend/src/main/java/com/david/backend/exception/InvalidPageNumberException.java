package com.david.backend.exception;

public class InvalidPageNumberException extends IllegalArgumentException {

    public InvalidPageNumberException(String message) {
        super(message);
    }
}
