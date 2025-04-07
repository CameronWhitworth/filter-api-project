package com.api.filter.comparison;

import com.api.filter.InvalidFilterException;

/**
 * Filter that checks if a property value is less than a given numeric value.
 */
public class LessThanFilter extends ComparisonFilter {
    private final int threshold;

    public LessThanFilter(String propertyName, int threshold) {
        super(propertyName);
        this.threshold = threshold;
    }

    @Override
    protected boolean evaluate(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue < threshold;
        } catch (NumberFormatException e) {
            throw new InvalidFilterException("Property value must be a valid integer", e);
        }
    }

    @Override
    public String toString() {
        return String.format("LessThanFilter[%s<%d]", propertyName, threshold);
    }
} 