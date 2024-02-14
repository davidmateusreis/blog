package com.david.backend.exception;

import java.util.NoSuchElementException;

public class NewsNotFoundException extends NoSuchElementException {

    public NewsNotFoundException(String message) {
        super(message);
    }
}
