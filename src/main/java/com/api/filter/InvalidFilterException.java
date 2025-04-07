package com.api.filter;

/**
 * Thrown when a filter is configured with invalid parameters.
 */
public class InvalidFilterException extends FilterException {
    public InvalidFilterException(String message) {
        super(message);
    }

    public InvalidFilterException(String message, Throwable cause) {
        super(message, cause);
    }
} 