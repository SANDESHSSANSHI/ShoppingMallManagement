package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import com.shopping_management.demo.model.Customer;

class CustomerTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        Customer customer = new Customer(1, 101);
     
        assertEquals(1, customer.getCustomerId());
        assertEquals(101, customer.getOrderId());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        Customer customer = new Customer();
    	
        customer.setCustomerId(2);
        customer.setOrderId(102);

        assertEquals(2, customer.getCustomerId());
        assertEquals(102, customer.getOrderId());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        Customer customer = new Customer(1, 101);
        assertNotNull(customer.getCustomerId());
        assertNotNull(customer.getOrderId());
        System.out.println("testAssertNotNull passed successfully; customerId and orderId are not null.");
    }

    @Test
    void testAssertNull() {
        Customer customer = new Customer(101, 5);
        assertNull(customer.getCustomerId());
        assertNull(customer.getOrderId());
        System.out.println("testAssertNull passed successfully; customerId and orderId are null.");
    }

    @Test
    void testAssertFalse() {
        Customer customer = new Customer(1, 101);
        assertFalse(customer.getCustomerId() == 0);
        System.out.println("testAssertFalse passed successfully; customerId is not zero.");
    }

    @Test
    void testAssumeTrue() {
        Customer customer = new Customer(1, 101);
        Assumptions.assumeTrue(customer.getCustomerId() == 1);
        System.out.println("testAssumeTrue passed successfully; assumption is true.");
    }

    @Test
    void testAssumingThat() {
        Customer customer = new Customer(1, 101);
        Assumptions.assumingThat(customer.getOrderId() > 100, 
            () -> System.out.println("testAssumingThat passed successfully; orderId is greater than 100."));
    }
}
