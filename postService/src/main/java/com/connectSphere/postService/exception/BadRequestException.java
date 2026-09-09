package com.connectSphere.postService.exception;

/**
 * Exception thrown when a bad request is made to the server.
 */
public class BadRequestException extends RuntimeException{
    /**
     * Constructs a new BadRequestException with the specified message.
     *
     * @param message The detail message.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
