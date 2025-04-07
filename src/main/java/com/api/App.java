package com.api;

import com.api.filter.*;
import static com.api.filter.Filters.*;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, String> user = new HashMap<>();
        user.put("role", "administrator");
        user.put("age", "35");

        // role=administrator AND age>30
        Filter filter = and(
            equalTo("role", "administrator"),
            greaterThan("age", 30)
        );

        boolean result = filter.matches(user);
        System.out.println("Filter result: " + result);
    }
}
