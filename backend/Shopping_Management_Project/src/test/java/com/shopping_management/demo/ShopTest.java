//package com.shopping_management.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import org.junit.jupiter.api.Test;
//
//import com.shopping_management.demo.model.Shop;
//
//class ShopTest {
//
//    @Test
//    void testParameterizedConstructorAndGetters() {
//        Shop shop = new Shop(1, "Electronics Hub", "Open", 101, 1001);
//
//        assertEquals(1, shop.getShopId());
//        assertEquals("Electronics Hub", shop.getShopName());
//        assertEquals("Open", shop.getShopStatus());
//        assertEquals(101, shop.getShopOwnerId());
//        assertEquals(1001, shop.getMallId());
//
//        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
//    }
//
//    @Test
//    void testSetters() {
//        Shop shop = new Shop();
//        shop.setShopId(2);
//        shop.setShopName("Grocery Mart");
//        shop.setShopStatus("Closed");
//        shop.setShopOwnerId(102);
//        shop.setMallId(1002);
//
//        assertEquals(2, shop.getShopId());
//        assertEquals("Grocery Mart", shop.getShopName());
//        assertEquals("Closed", shop.getShopStatus());
//        assertEquals(102, shop.getShopOwnerId());
//        assertEquals(1002, shop.getMallId());
//
//        System.out.println("testSetters passed successfully.");
//    }
//
//    @Test
//    void testAssertNotNull() {
//        Shop shop = new Shop(1, "Fashion Store", "Open", 103, 1003);
//
//        assertNotNull(shop.getShopId());
//        assertNotNull(shop.getShopName());
//        assertNotNull(shop.getShopStatus());
//        assertNotNull(shop.getShopOwnerId());
//        assertNotNull(shop.getMallId());
//
//        System.out.println("testAssertNotNull passed successfully; fields are not null.");
//    }
//
//    @Test
//    void testAssertNull() {
//        Shop shop = new Shop();
//        assertNull(shop.getShopId());
//        assertNull(shop.getShopName());
//        assertNull(shop.getShopStatus());
//        assertNull(shop.getShopOwnerId());
//        assertNull(shop.getMallId());
//
//        System.out.println("testAssertNull passed successfully; all fields are null.");
//    }
//
//    @Test
//    void testToString() {
//        Shop shop = new Shop(1, "Sports Goods", "Open", 104, 1004);
//        String expected = "Shop [shopId=1, shopName=Sports Goods, shopStatus=Open, " +
//                          "shopOwnerId=104, mallId=1004]";
//        assertEquals(expected, shop.toString());
//
//        System.out.println("testToString passed successfully.");
//    }
//}
