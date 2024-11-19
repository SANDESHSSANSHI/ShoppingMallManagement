package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import com.shopping_management.demo.model.Cart;
import java.util.Arrays;
import java.util.List;

class CartTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        List<Integer> items = Arrays.asList(1, 2, 3);
        Cart cart = new Cart(1, 101, 201, items);
     
        assertEquals(1, cart.getCartid());
        assertEquals(101, cart.getCustomerId());
        assertEquals(201, cart.getShopId());
        assertEquals(items, cart.getItemIds());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        Cart cart = new Cart();
        List<Integer> items = Arrays.asList(4, 5, 6);
    	
        cart.setCartid(2);
        cart.setCustomerId(102);
        cart.setShopId(202);
        cart.setItemIds(items);

        assertEquals(2, cart.getCartid());
        assertEquals(102, cart.getCustomerId());
        assertEquals(202, cart.getShopId());
        assertEquals(items, cart.getItemIds());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        Cart cart = new Cart(null, 101, 201, Arrays.asList(1, 2, 3));
        assertNotNull(cart.getCartid());
        assertNotNull(cart.getCustomerId());
        System.out.println("testAssertNotNull passed successfully; cartId and customerId are not null.");
    }

    @Test
    void testAssertNull() {
        Cart cart = new Cart(1, null, 201, null);
        assertNull(cart.getCustomerId());
        assertNull(cart.getItemIds());
        System.out.println("testAssertNull passed successfully; customerId and itemIds are null.");
    }

    @Test
    void testAssertFalse() {
        Cart cart = new Cart(1, 101, 201, Arrays.asList(1, 2, 3));
        assertFalse(cart.getItemCount(cart.getItemIds()) == 0);
        System.out.println("testAssertFalse passed successfully; itemCount is not zero.");
    }

    @Test
    void testAssumeTrue() {
        Cart cart = new Cart(1, 101, 201, Arrays.asList(1, 2, 3));
        Assumptions.assumeTrue(cart.getCartid() == 1);
        System.out.println("testAssumeTrue passed successfully; assumption is true.");
    }

    @Test
    void testAssumingThat() {
        Cart cart = new Cart(1, 101, 201, Arrays.asList(1, 2, 3));
        Assumptions.assumingThat(cart.getItemCount(cart.getItemIds()) > 2, 
            () -> System.out.println("testAssumingThat passed successfully; itemCount is greater than 2."));
    }
}
