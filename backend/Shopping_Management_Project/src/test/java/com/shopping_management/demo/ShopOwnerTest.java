package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.shopping_management.demo.model.ShopOwner;

class ShopOwnerTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        ShopOwner shopOwner = new ShopOwner(1, 101);

        assertEquals(1, shopOwner.getShopOwnerId());
        assertEquals(101, shopOwner.getShopId());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        ShopOwner shopOwner = new ShopOwner();
        shopOwner.setShopOwnerId(2);
        shopOwner.setShopId(102);

        assertEquals(2, shopOwner.getShopOwnerId());
        assertEquals(102, shopOwner.getShopId());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        ShopOwner shopOwner = new ShopOwner(1, 101);

        assertNotNull(shopOwner.getShopOwnerId());
        assertNotNull(shopOwner.getShopId());

        System.out.println("testAssertNotNull passed successfully; fields are not null.");
    }

    @Test
    void testAssertNull() {
        ShopOwner shopOwner = new ShopOwner();
        assertNull(shopOwner.getShopOwnerId());
        assertNull(shopOwner.getShopId());

        System.out.println("testAssertNull passed successfully; all fields are null.");
    }

    @Test
    void testToString() {
        ShopOwner shopOwner = new ShopOwner(1, 101);
        String expected = "ShopOwner [shopOwnerId=1, shopId=101]";
        assertEquals(expected, shopOwner.toString());

        System.out.println("testToString passed successfully.");
    }
}
