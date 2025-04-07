package com.api.filter;

import java.util.Map;

public interface Filter {
    /**
     * Returns true if the given resource matches the filter, false otherwise.
     * A resource is represented as Map<String, String>, where the keys
     * are property names (case sensitive) and the values are property values (case insensitive).
     */
    boolean matches(Map<String, String> resource);
}
