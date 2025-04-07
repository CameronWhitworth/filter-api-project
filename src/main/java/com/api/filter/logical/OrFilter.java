package com.api.filter.logical;

import com.api.filter.Filter;
import java.util.Map;
import java.util.Arrays;

/**
 * Filter that combines multiple filters using logical OR.
 * Returns true if any child filter returns true.
 */
public class OrFilter extends LogicalFilter {
    public OrFilter(Filter... filters) {
        super(filters);
    }

    @Override
    protected boolean evaluate(Map<String, String> resource) {
        for (Filter filter : filters) {
            if (filter.matches(resource)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("OrFilter[%s]", String.join(", ", Arrays.stream(filters)
            .map(Filter::toString)
            .toArray(String[]::new)));
    }
} 