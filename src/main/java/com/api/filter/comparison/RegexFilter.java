package com.api.filter.comparison;

import com.api.filter.InvalidFilterException;
import java.util.regex.Pattern;

/**
 * Filter that checks if a property value matches a given regular expression.
 */
public class RegexFilter extends ComparisonFilter {
    private final Pattern pattern;

    public RegexFilter(String propertyName, String regex) {
        super(propertyName);
        if (regex == null) {
            throw new InvalidFilterException("Regular expression cannot be null");
        }
        try {
            this.pattern = Pattern.compile(regex);
        } catch (Exception e) {
            throw new InvalidFilterException("Invalid regular expression: " + regex, e);
        }
    }

    @Override
    protected boolean evaluate(String value) {
        return pattern.matcher(value).matches();
    }

    @Override
    public String toString() {
        return String.format("RegexFilter[%s~=%s]", propertyName, pattern.pattern());
    }
} 