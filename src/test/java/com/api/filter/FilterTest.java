package com.api.filter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static com.api.filter.Filters.*;

public class FilterTest {

    private Map<String, String> resource;

    @Before
    public void setUp() {
        resource = new HashMap<>();
        resource.put("role", "administrator");
        resource.put("age", "35");
        resource.put("firstname", "Joe");
        resource.put("email", "joe@example.com");
    }

    @Test
    public void testBooleanLiterals() {
        assertTrue(TRUE.matches(resource));
        assertTrue(TRUE.matches(null));
        assertTrue(TRUE.matches(new HashMap<>()));

        assertFalse(FALSE.matches(resource));
        assertFalse(FALSE.matches(null));
        assertFalse(FALSE.matches(new HashMap<>()));
    }

    @Test
    public void testPresentFilter() {
        Filter presentRole = present("role");
        Filter presentDoesNotExist = present("nonexistent");
        
        assertTrue(presentRole.matches(resource));
        assertFalse(presentDoesNotExist.matches(resource));
        assertFalse(presentRole.matches(null));
        assertFalse(presentRole.matches(new HashMap<>()));
    }

    @Test(expected = InvalidFilterException.class)
    public void testPresentFilterWithInvalidPropertyName() {
        present(null);
    }

    @Test(expected = InvalidFilterException.class)
    public void testPresentFilterWithEmptyPropertyName() {
        present("");
    }

    @Test
    public void testEqualsFilter() {
        Filter equalsAdmin = equalTo("role", "administrator");
        Filter equalsCaseInsensitive = equalTo("firstname", "joe");
        Filter equalsFail = equalTo("role", "user");

        assertTrue(equalsAdmin.matches(resource));
        assertTrue(equalsCaseInsensitive.matches(resource));
        assertFalse(equalsFail.matches(resource));
        assertFalse(equalsAdmin.matches(null));
        assertFalse(equalsAdmin.matches(new HashMap<>()));
    }

    @Test(expected = InvalidFilterException.class)
    public void testEqualsFilterWithNullValue() {
        equalTo("role", null);
    }

    @Test
    public void testGreaterThanFilter() {
        Filter greaterThan30 = greaterThan("age", 30);
        Filter greaterThan40 = greaterThan("age", 40);

        assertTrue(greaterThan30.matches(resource)); 
        assertFalse(greaterThan40.matches(resource));
        assertFalse(greaterThan30.matches(null));
        assertFalse(greaterThan30.matches(new HashMap<>()));
    }

    @Test(expected = InvalidFilterException.class)
    public void testGreaterThanFilterWithNonNumericValue() {
        resource.put("age", "not-a-number");
        greaterThan("age", 30).matches(resource);
    }

    @Test
    public void testLessThanFilter() {
        Filter lessThan40 = lessThan("age", 40);
        Filter lessThan30 = lessThan("age", 30);

        assertTrue(lessThan40.matches(resource)); 
        assertFalse(lessThan30.matches(resource)); 
        assertFalse(lessThan40.matches(null));
        assertFalse(lessThan40.matches(new HashMap<>()));
    }

    @Test
    public void testAndFilter() {
        Filter adminOlderThan30 = and(
            equalTo("role", "administrator"),
            greaterThan("age", 30)
        );
        assertTrue(adminOlderThan30.matches(resource));

        resource.put("age", "20");
        assertFalse(adminOlderThan30.matches(resource));

        assertFalse(adminOlderThan30.matches(null));
    }

    @Test(expected = FilterException.class)
    public void testAndFilterWithNullFilters() {
        and((Filter[]) null);
    }

    @Test(expected = FilterException.class)
    public void testAndFilterWithEmptyFilters() {
        and();
    }

    @Test(expected = FilterException.class)
    public void testAndFilterWithNullFilter() {
        and(equalTo("role", "admin"), null);
    }

    @Test
    public void testOrFilter() {
        Filter roleIsAdminOrUser = or(
            equalTo("role", "administrator"),
            equalTo("role", "user")
        );
        assertTrue(roleIsAdminOrUser.matches(resource));

        resource.put("role", "guest");
        assertFalse(roleIsAdminOrUser.matches(resource));

        assertFalse(roleIsAdminOrUser.matches(null));
    }

    @Test
    public void testNotFilter() {
        Filter notAdmin = not(equalTo("role", "administrator"));
        assertFalse(notAdmin.matches(resource));  

        resource.put("role", "guest");
        assertTrue(notAdmin.matches(resource));
        assertTrue(notAdmin.matches(null));
    }

    @Test(expected = FilterException.class)
    public void testNotFilterWithNullFilter() {
        not(null);
    }

    @Test
    public void testRegexFilter() {
        Filter emailRegex = regex("email", ".*@example\\.com");
        assertTrue(emailRegex.matches(resource));

        resource.put("email", "joe@gmail.com");
        assertFalse(emailRegex.matches(resource));

        assertFalse(emailRegex.matches(null));
    }

    @Test(expected = InvalidFilterException.class)
    public void testRegexFilterWithNullRegex() {
        regex("email", null);
    }

    @Test(expected = InvalidFilterException.class)
    public void testRegexFilterWithInvalidRegex() {
        regex("email", "[invalid");
    }

    @Test
    public void testComplexFilterCombinations() {
        Filter complexFilter = or(
            and(
                equalTo("role", "administrator"),
                greaterThan("age", 30)
            ),
            and(
                equalTo("role", "user"),
                lessThan("age", 20)
            )
        );

        assertTrue(complexFilter.matches(resource));

        resource.put("role", "user");
        resource.put("age", "15");
        assertTrue(complexFilter.matches(resource));

        resource.put("age", "25");
        assertFalse(complexFilter.matches(resource));
    }

    @Test
    public void testToString() {
        assertEquals("AndFilter[EqualsFilter[role=administrator], GreaterThanFilter[age>30]]",
            and(equalTo("role", "administrator"), greaterThan("age", 30)).toString());
        
        assertEquals("OrFilter[EqualsFilter[role=admin], EqualsFilter[role=user]]",
            or(equalTo("role", "admin"), equalTo("role", "user")).toString());
        
        assertEquals("NotFilter[EqualsFilter[role=admin]]",
            not(equalTo("role", "admin")).toString());
        
        assertEquals("RegexFilter[email~=.*@example\\.com]",
            regex("email", ".*@example\\.com").toString());
    }
}
