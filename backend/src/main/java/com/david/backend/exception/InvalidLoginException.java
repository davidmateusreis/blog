package com.david.backend.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidLoginException extends AuthenticationException {

    public InvalidLoginException(String message) {
        super(message);
    }
}
