package com.david.backend.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class RoleNotFoundException extends BadCredentialsException {

    public RoleNotFoundException(String message) {
        super(message);
    }
}
