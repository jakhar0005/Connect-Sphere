package com.connectSphere.postService.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

/**
 * Represents an error response for API requests.
 */
@Getter
@Setter
public class APIError {
    /**
     * The error message associated with the API error.
     */
    private String error;

    /**
     * The HTTP status code associated with the API error.
     */
    private HttpStatusCode statusCode;

    /**
     * The timestamp indicating when the API error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * Default constructor that initializes the timestamp to the current date and time.
     */
    public APIError() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor that initializes the APIError with a specific error message
     * and HTTP status code.
     *
     * @param error The error message associated with the API error.
     * @param statusCode The HTTP status code associated with the API error.
     */
    public APIError(final String error
        , final HttpStatusCode statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}
