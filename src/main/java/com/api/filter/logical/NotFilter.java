package com.api.filter.logical;

import com.api.filter.Filter;
import java.util.Map;

/**
 * Filter that negates the result of another filter.
 * Returns true if the child filter returns false, and vice versa.
 */
public class NotFilter extends LogicalFilter {
    public NotFilter(Filter filter) {
        super(filter);
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return !filters[0].matches(resource);
    }

    @Override
    protected boolean evaluate(Map<String, String> resource) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("NotFilter[%s]", filters[0]);
    }
} 