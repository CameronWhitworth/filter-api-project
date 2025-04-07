package com.api.filter.comparison;

import com.api.filter.InvalidFilterException;

/**
 * Filter that checks if a property value equals (case-insensitive) a given value.
 */
public class EqualsFilter extends ComparisonFilter {
    private final String expectedValue;

    public EqualsFilter(String propertyName, String expectedValue) {
        super(propertyName);
        if (expectedValue == null) {
            throw new InvalidFilterException("Expected value cannot be null");
        }
        this.expectedValue = expectedValue;
    }

    @Override
    protected boolean evaluate(String value) {
        return value.equalsIgnoreCase(expectedValue);
    }

    @Override
    public String toString() {
        return String.format("EqualsFilter[%s=%s]", propertyName, expectedValue);
    }
} 