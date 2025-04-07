package com.api.filter.comparison;

import com.api.filter.Filter;
import com.api.filter.InvalidFilterException;
import java.util.Map;

/**
 * Base class for filters that compare property values.
 */
public abstract class ComparisonFilter implements Filter {
    protected final String propertyName;

    protected ComparisonFilter(String propertyName) {
        if (propertyName == null || propertyName.trim().isEmpty()) {
            throw new InvalidFilterException("Property name cannot be null or empty");
        }
        this.propertyName = propertyName;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if (resource == null) {
            return false;
        }
        String value = resource.get(propertyName);
        if (value == null) {
            return false;
        }
        return evaluate(value);
    }

    protected abstract boolean evaluate(String value);
} 