package com.api.filter.logical;

import com.api.filter.Filter;
import com.api.filter.FilterException;
import java.util.Map;

/**
 * Base class for logical filters that combine multiple filters.
 */
public abstract class LogicalFilter implements Filter {
    protected final Filter[] filters;

    protected LogicalFilter(Filter... filters) {
        if (filters == null || filters.length == 0) {
            throw new FilterException("Filters array cannot be null or empty");
        }
        for (Filter filter : filters) {
            if (filter == null) {
                throw new FilterException("Individual filters cannot be null");
            }
        }
        this.filters = filters;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if (resource == null) {
            return false;
        }
        return evaluate(resource);
    }

    protected abstract boolean evaluate(Map<String, String> resource);
} 