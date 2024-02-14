package com.david.backend.exception;

import org.springframework.dao.DataAccessException;

public class ContactMessageException extends DataAccessException {

    public ContactMessageException(String message) {
        super(message);
    }
}
