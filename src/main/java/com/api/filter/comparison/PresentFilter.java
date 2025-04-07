package com.api.filter.comparison;

import java.util.Map;

/**
 * Filter that checks if a property is present in the resource.
 */
public class PresentFilter extends ComparisonFilter {
    public PresentFilter(String propertyName) {
        super(propertyName);
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if (resource == null) {
            return false;
        }
        return resource.containsKey(propertyName);
    }

    @Override
    protected boolean evaluate(String value) {
        return true; // This method is not used for PresentFilter
    }

    @Override
    public String toString() {
        return String.format("PresentFilter[%s]", propertyName);
    }
} 