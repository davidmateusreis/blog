package com.david.backend.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class UserNotActiveException extends BadCredentialsException {

    public UserNotActiveException(String message) {
        super(message);
    }
}
