package com.rogeriogregorio.bookmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class BookManagementExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericError(Exception ex) {

        StandardError genericError = new StandardError();

        genericError.setTimeStamp(Instant.now());
        genericError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        genericError.setError("Internal server error.");
        genericError.setMessage(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(genericError);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<StandardError> bookNotFoundException(BookNotFoundException ex) {

        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Book not found.");
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(BookCreationException.class)
    public ResponseEntity<StandardError> bookCreationException(BookCreationException ex) {

        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setError("Error creating book.");
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<StandardError> bookAlreadyExistsException(BookAlreadyExistsException ex) {

        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Book already exists.");
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(BookUpdateException.class)
    public ResponseEntity<StandardError> bookUpdateException(BookUpdateException ex) {

        StandardError error = new StandardError();

        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setError("Error updating book");
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
