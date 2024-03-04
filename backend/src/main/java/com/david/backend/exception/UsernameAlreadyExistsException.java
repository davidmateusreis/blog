package com.david.backend.exception;

import org.springframework.dao.DataAccessException;

public class UsernameAlreadyExistsException extends DataAccessException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}