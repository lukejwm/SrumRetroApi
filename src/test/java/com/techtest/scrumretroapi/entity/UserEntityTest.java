package com.techtest.scrumretroapi.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test case to validate that User entity annotations can be used
 */
public class UserEntityTest {
    @Test
    void testConstructorAndGetters() {
        User user = new User(1L, "John", "Doe", "johndoe", "john@example.com");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testSetterAndGetters() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setEmail("john@example.com");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testToString() {
        User user = new User(1L, "John", "Doe", "johndoe", "john@example.com");

        String expected = "User(id=1, firstName=John, lastName=Doe, username=johndoe, email=john@example.com)";
        assertEquals(expected, user.toString());
    }

    @Test
    void testDataPropertiesAreJsonProperties() throws Exception {
        User user = new User(1L, "John", "Doe", "johndoe", "john@example.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        // Check if data is converted to JSON with @JSONProperty annotation
        assertTrue(json.contains("\"id\":1")); // Assuming "id" is always included
        assertTrue(json.contains("\"firstName\":\"John\""));
        assertTrue(json.contains("\"lastName\":\"Doe\""));
        assertTrue(json.contains("\"username\":\"johndoe\""));
        assertTrue(json.contains("\"email\":\"john@example.com\""));
    }
}
