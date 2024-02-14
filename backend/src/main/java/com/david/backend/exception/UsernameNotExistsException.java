package com.david.backend.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class UsernameNotExistsException extends BadCredentialsException {

    public UsernameNotExistsException(String message) {
        super(message);
    }
}
