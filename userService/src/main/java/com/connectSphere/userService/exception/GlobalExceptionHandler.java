package com.connectSphere.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns a ResponseEntity with an
     * APIError and HTTP status code 404 (NOT_FOUND).
     *
     * @param exception ResourceNotFoundException that was thrown.
     *
     * @return ResponseEntity containing the APIError and HTTP status code 404
     * (NOT_FOUND).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFoundException(final ResourceNotFoundException exception) {
        final var error = new APIError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIError> handleBadRequestException(final BadRequestException exception) {
        final var error = new APIError(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIError> handleRuntimeException(final RuntimeException exception) {
        final var error = new APIError(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
