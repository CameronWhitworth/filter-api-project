package com.api.filter;

/**
 * Base exception class for all filter-related exceptions.
 */
public class FilterException extends RuntimeException {
    public FilterException(String message) {
        super(message);
    }

    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }
} 