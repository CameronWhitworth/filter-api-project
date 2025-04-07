package com.api.filter;

import java.util.Map;

public interface Filter {
    /**
     * Returns true if the given resource matches the filter, false otherwise.
     */
    boolean matches(Map<String, String> resource);
}
