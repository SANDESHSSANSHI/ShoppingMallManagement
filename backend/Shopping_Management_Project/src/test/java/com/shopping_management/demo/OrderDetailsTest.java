package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import com.shopping_management.demo.model.OrderDetails;

class OrderDetailsTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        LocalDate purchaseDate = LocalDate.of(2024, 9, 29);
        OrderDetails orderDetails = new OrderDetails(1, purchaseDate, "Credit Card", 1001);

        assertEquals(1, orderDetails.getOrderId());
        assertEquals(purchaseDate, orderDetails.getDateOfPurchase());
        assertEquals("Credit Card", orderDetails.getPaymentMode());
        assertEquals(1001, orderDetails.getCartId());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        OrderDetails orderDetails = new OrderDetails();
        
        LocalDate purchaseDate = LocalDate.of(2024, 9, 29);
        orderDetails.setOrderId(2);
        orderDetails.setDateOfPurchase(purchaseDate);
        orderDetails.setPaymentMode("Debit Card");
        orderDetails.setCartId(1002);

        assertEquals(2, orderDetails.getOrderId());
        assertEquals(purchaseDate, orderDetails.getDateOfPurchase());
        assertEquals("Debit Card", orderDetails.getPaymentMode());
        assertEquals(1002, orderDetails.getCartId());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        LocalDate purchaseDate = LocalDate.of(2024, 9, 29);
        OrderDetails orderDetails = new OrderDetails(1, purchaseDate, "Credit Card", 1001);
        
        assertNotNull(orderDetails.getOrderId());
        assertNotNull(orderDetails.getDateOfPurchase());
        assertNotNull(orderDetails.getPaymentMode());
        assertNotNull(orderDetails.getCartId());

        System.out.println("testAssertNotNull passed successfully; fields are not null.");
    }

    @Test
    void testAssertNull() {
        OrderDetails orderDetails = new OrderDetails();
        assertNull(orderDetails.getOrderId());
        assertNull(orderDetails.getDateOfPurchase());
        assertNull(orderDetails.getPaymentMode());
        assertNull(orderDetails.getCartId());

        System.out.println("testAssertNull passed successfully; all fields are null.");
    }

    @Test
    void testAssertFalse() {
        OrderDetails orderDetails = new OrderDetails(1, LocalDate.of(2024, 9, 29), "Credit Card", 1001);
        assertFalse(orderDetails.getOrderId() == 0);
        System.out.println("testAssertFalse passed successfully; orderId is not zero.");
    }

    @Test
    void testAssumeTrue() {
        OrderDetails orderDetails = new OrderDetails(1, LocalDate.of(2024, 9, 29), "Credit Card", 1001);
        Assumptions.assumeTrue(orderDetails.getOrderId() == 1);
        System.out.println("testAssumeTrue passed successfully; assumption is true.");
    }

    @Test
    void testAssumingThat() {
        OrderDetails orderDetails = new OrderDetails(1, LocalDate.of(2024, 9, 29), "Credit Card", 1001);
        Assumptions.assumingThat(orderDetails.getPaymentMode() != null, 
            () -> System.out.println("testAssumingThat passed successfully; paymentMode is not null."));
    }

    @Test
    void testToString() {
        LocalDate purchaseDate = LocalDate.of(2024, 9, 29);
        OrderDetails orderDetails = new OrderDetails(1, purchaseDate, "Credit Card", 1001);
        String expected = "OrderDetails [orderId=1, dateOfPurchase=" + purchaseDate + 
                          ", paymentMode=Credit Card, cartId=1001]";
        assertEquals(expected, orderDetails.toString());

        System.out.println("testToString passed successfully.");
    }
}
