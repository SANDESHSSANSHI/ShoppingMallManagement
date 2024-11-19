package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import com.shopping_management.demo.model.Employee;

import java.time.LocalDate;

class EmployeeTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        LocalDate dob = LocalDate.of(1990, 5, 15);
        Employee employee = new Employee(1, "John Doe", dob, 50000.0, "123 Street", "Manager", 101);

        assertEquals(1, employee.getEmployeeId());
        assertEquals("John Doe", employee.getEmployeeName());
        assertEquals(dob, employee.getEmployeeDateOfBirth());
        assertEquals(50000.0, employee.getEmployeeSalary());
        assertEquals("123 Street", employee.getEmployeeAddress());
        assertEquals("Manager", employee.getEmployeeDesignation());
        assertEquals(101, employee.getShopId());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        Employee employee = new Employee();
        LocalDate dob = LocalDate.of(1992, 6, 20);
        employee.setEmployeeId(2);
        employee.setEmployeeName("Jane Doe");
        employee.setEmployeeDateOfBirth(dob);
        employee.setEmployeeSalary(60000.0);
        employee.setEmployeeAddress("456 Avenue");
        employee.setEmployeeDesignation("Assistant Manager");
        employee.setShopId(102);

        assertEquals(2, employee.getEmployeeId());
        assertEquals("Jane Doe", employee.getEmployeeName());
        assertEquals(dob, employee.getEmployeeDateOfBirth());
        assertEquals(60000.0, employee.getEmployeeSalary());
        assertEquals("456 Avenue", employee.getEmployeeAddress());
        assertEquals("Assistant Manager", employee.getEmployeeDesignation());
        assertEquals(102, employee.getShopId());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        Employee employee = new Employee(1, "John Doe", LocalDate.of(1990, 5, 15), 50000.0, "123 Street", "Manager", 101);
        assertNotNull(employee.getEmployeeId());
        assertNotNull(employee.getEmployeeName());
        System.out.println("testAssertNotNull passed successfully; employeeId and employeeName are not null.");
    }

    @Test
    void testAssertNull() {
        Employee employee = new Employee(null, null, null, null, null, null, null);
        assertNull(employee.getEmployeeId());
        assertNull(employee.getEmployeeName());
        assertNull(employee.getEmployeeDateOfBirth());
        assertNull(employee.getEmployeeSalary());
        assertNull(employee.getEmployeeAddress());
        assertNull(employee.getEmployeeDesignation());
        assertNull(employee.getShopId());
        System.out.println("testAssertNull passed successfully; all fields are null.");
    }

    @Test
    void testAssertFalse() {
        Employee employee = new Employee(1, "John Doe", LocalDate.of(1990, 5, 15), 50000.0, "123 Street", "Manager", 101);
        assertFalse(employee.getEmployeeSalary() == 0);
        System.out.println("testAssertFalse passed successfully; employeeSalary is not zero.");
    }

    @Test
    void testAssumeTrue() {
        Employee employee = new Employee(1, "John Doe", LocalDate.of(1990, 5, 15), 50000.0, "123 Street", "Manager", 101);
        Assumptions.assumeTrue(employee.getEmployeeId() == 1);
        System.out.println("testAssumeTrue passed successfully; assumption is true.");
    }

    @Test
    void testAssumingThat() {
        Employee employee = new Employee(1, "John Doe", LocalDate.of(1990, 5, 15), 50000.0, "123 Street", "Manager", 101);
        Assumptions.assumingThat(employee.getEmployeeSalary() > 30000, 
            () -> System.out.println("testAssumingThat passed successfully; employeeSalary is greater than 30000."));
    }
}
