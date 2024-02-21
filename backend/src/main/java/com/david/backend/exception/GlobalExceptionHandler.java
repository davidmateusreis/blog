package com.david.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNewsNotFoundException(NewsNotFoundException e) {
        ErrorResponse newsNotFoundException = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newsNotFoundException);
    }

    @ExceptionHandler(InvalidPageNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPageNumberException(InvalidPageNumberException e) {
        ErrorResponse invalidPageNumberException = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidPageNumberException);
    }

    @ExceptionHandler(UsernameNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotExistsException(UsernameNotExistsException e) {
        ErrorResponse usernameNotExistsResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usernameNotExistsResponse);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        ErrorResponse usernameAlreadyExistsResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(usernameAlreadyExistsResponse);
    }

    @ExceptionHandler(ContactMessageException.class)
    public ResponseEntity<ErrorResponse> handleContactMessageException(ContactMessageException e) {
        ErrorResponse contactMessageResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(contactMessageResponse);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<ErrorResponse> handleUserNotActiveException(UserNotActiveException e) {
        ErrorResponse userNotActiveResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userNotActiveResponse);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        ErrorResponse invalidPasswordResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(invalidPasswordResponse);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException e) {
        ErrorResponse roleNotFoundResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(roleNotFoundResponse);
    }

    @ExceptionHandler(NewsFetchAndSaveException.class)
    public ResponseEntity<ErrorResponse> handleNewsFetchAndSaveException(NewsFetchAndSaveException e) {
        ErrorResponse newsFetchAndSaveExceptionResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(newsFetchAndSaveExceptionResponse);
    }

    @ExceptionHandler(NewsUpdateException.class)
    public ResponseEntity<ErrorResponse> handleNewsUpdateException(NewsUpdateException e) {
        ErrorResponse newsUpdateExceptionResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(newsUpdateExceptionResponse);
    }
}
