# Filter API Implementation (Take Home)

This project implements a flexible and extensible Filter API for querying resources represented as `Map<String, String>`. The implementation provides a type-safe way to construct and evaluate complex filter conditions.


**Time Spent**: Approximately 3 hours

## Overview

The Filter API allows clients to:
- Create filters using a builder pattern
- Combine filters using logical operators (AND, OR, NOT)
- Compare property values using various operators
- Handle null values and missing properties gracefully
- Generate string representations of filters

## Implementation Details

### Core Components

1. **Filter Interface**
   - Base interface defining the contract for all filters
   - Single method `matches(Map<String, String>)` to evaluate filters

2. **Filter Categories**
   - **Logical Filters** (`com.api.filter.logical`)
     - `AndFilter`
     - `OrFilter`
     - `NotFilter`
   
   - **Comparison Filters** (`com.api.filter.comparison`)
     - `EqualsFilter`
     - `GreaterThanFilter`
     - `LessThanFilter`
     - `RegexFilter`
     - `PresentFilter`

3. **Factory Class**
   - `Filters`: Static factory methods for creating filters
   - Provides a fluent API for filter construction

4. **Misc**
   - `.gitignore`: Good practice repo managment
   - `README`: Hi :D

### Design Decisions

   - Used abstract base classes (`LogicalFilter`, `ComparisonFilter`) to ensure consistent behavior
   - Implemented proper exception handling with custom exceptions
   - Clear separation of concerns between filter types
   - Easy to add new filter types by implementing the `Filter` interface
   - Base classes provide common functionality

## Dependencies and Prerequisites

- **Java**: The project was built using java 17
- **JUnit 4**: For unit testing
- **Maven**: For dependency management and build automation

## How to Run

### Building the Project
```bash
mvn clean install
```

### Running Tests
```bash
mvn test
```

### Example Usage

```java
// Create a resource
Map<String, String> user = new HashMap<>();
user.put("role", "administrator");
user.put("age", "35");

// Create a filter using the factory methods
Filter filter = and(
    equalTo("role", "administrator"),
    greaterThan("age", 30)
);

// Evaluate the filter
boolean matches = filter.matches(user);  // returns true
```

## Testing

The project includes comprehensive unit tests covering:
- Basic filter operations
- Edge cases (null values, missing properties)
- Complex filter combinations
- Error conditions
- String representations

## TODO / Ideas

Here's some stuff could add:

1. **More Filter Types**
   - String filters: contains, starts/ends with
   - Number ranges (between X and Y)
   - Date comparisons
   - Case-sensitive string matching

2. **Better Performance**
   - Cache regex patterns
   - Cache filter results
   - Run filters in parallel

3. **Dev Support**
   - Better debug logging
   - Performance metrics
   - More examples/docs
