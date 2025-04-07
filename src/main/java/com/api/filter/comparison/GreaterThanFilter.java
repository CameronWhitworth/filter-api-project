package com.api.filter.comparison;

import com.api.filter.InvalidFilterException;

/**
 * Filter that checks if a property value is greater than a given numeric value.
 */
public class GreaterThanFilter extends ComparisonFilter {
    private final int threshold;

    public GreaterThanFilter(String propertyName, int threshold) {
        super(propertyName);
        this.threshold = threshold;
    }

    @Override
    protected boolean evaluate(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue > threshold;
        } catch (NumberFormatException e) {
            throw new InvalidFilterException("Property value must be a valid integer", e);
        }
    }

    @Override
    public String toString() {
        return String.format("GreaterThanFilter[%s>%d]", propertyName, threshold);
    }
} 