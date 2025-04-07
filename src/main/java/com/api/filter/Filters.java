package com.api.filter;

import com.api.filter.comparison.*;
import com.api.filter.logical.*;

/**
 * Factory class for creating filters.
 */
public final class Filters {
    // Prevent instantiation
    private Filters() {}

    // Boolean literals
    public static final Filter TRUE = resource -> true;
    public static final Filter FALSE = resource -> false;

    // Comparison filters
    public static Filter present(String propertyName) {
        return new PresentFilter(propertyName);
    }

    public static Filter equalTo(String propertyName, String value) {
        return new EqualsFilter(propertyName, value);
    }

    public static Filter lessThan(String propertyName, int value) {
        return new LessThanFilter(propertyName, value);
    }

    public static Filter greaterThan(String propertyName, int value) {
        return new GreaterThanFilter(propertyName, value);
    }

    public static Filter regex(String propertyName, String regex) {
        return new RegexFilter(propertyName, regex);
    }

    // Logical filters
    public static Filter and(Filter... filters) {
        return new AndFilter(filters);
    }

    public static Filter or(Filter... filters) {
        return new OrFilter(filters);
    }

    public static Filter not(Filter filter) {
        return new NotFilter(filter);
    }
}
