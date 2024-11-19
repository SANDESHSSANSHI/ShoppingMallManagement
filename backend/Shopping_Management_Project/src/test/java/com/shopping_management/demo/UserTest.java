package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.shopping_management.demo.model.User;

class UserTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        User user = new User(1, "John Doe", "admin", "password123", "1234567890", "johndoe@example.com");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("admin", user.getType());
        assertEquals("password123", user.getPassword());
        assertEquals("1234567890", user.getMobileNumber());
        assertEquals("johndoe@example.com", user.getMail());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        User user = new User();
        user.setId(2);
        user.setName("Jane Smith");
        user.setType("user");
        user.setPassword("mypassword");
        user.setMobileNumber("0987654321");
        user.setMail("janesmith@example.com");

        assertEquals(2, user.getId());
        assertEquals("Jane Smith", user.getName());
        assertEquals("user", user.getType());
        assertEquals("mypassword", user.getPassword());
        assertEquals("0987654321", user.getMobileNumber());
        assertEquals("janesmith@example.com", user.getMail());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        User user = new User(1, "John Doe", "admin", "password123", "1234567890", "johndoe@example.com");

        assertNotNull(user.getId());
        assertNotNull(user.getName());
        assertNotNull(user.getType());
        assertNotNull(user.getPassword());
        assertNotNull(user.getMobileNumber());
        assertNotNull(user.getMail());

        System.out.println("testAssertNotNull passed successfully; fields are not null.");
    }

    @Test
    void testAssertNull() {
        User user = new User();
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getType());
        assertNull(user.getPassword());
        assertNull(user.getMobileNumber());
        assertNull(user.getMail());

        System.out.println("testAssertNull passed successfully; all fields are null.");
    }

    @Test
    void testToString() {
        User user = new User(1, "John Doe", "admin", "password123", "1234567890", "johndoe@example.com");
        String expected = "User [id=1, name=John Doe, type=admin, password=password123, mobileNumber=1234567890, mail=johndoe@example.com]";
        assertEquals(expected, user.toString());

        System.out.println("testToString passed successfully.");
    }
}
