package com.api.filter.logical;

import com.api.filter.Filter;
import java.util.Map;
import java.util.Arrays;

/**
 * Filter that combines multiple filters using logical AND.
 * Returns true only if all child filters return true.
 */
public class AndFilter extends LogicalFilter {
    public AndFilter(Filter... filters) {
        super(filters);
    }

    @Override
    protected boolean evaluate(Map<String, String> resource) {
        for (Filter filter : filters) {
            if (!filter.matches(resource)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("AndFilter[%s]", String.join(", ", Arrays.stream(filters)
            .map(Filter::toString)
            .toArray(String[]::new)));
    }
} 