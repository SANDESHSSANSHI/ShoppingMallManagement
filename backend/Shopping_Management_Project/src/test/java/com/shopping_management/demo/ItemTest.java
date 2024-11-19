//package com.shopping_management.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import org.junit.jupiter.api.Assumptions;
//import org.junit.jupiter.api.Test;
//
//import com.shopping_management.demo.model.Item;
//
//import java.time.LocalDate;
//
//class ItemTest {
//
//    @Test
//    void testParameterizedConstructorAndGetters() {
//        LocalDate expiryDate = LocalDate.of(2025, 12, 31);
//        Item item = new Item(1, "Laptop", "Dell", expiryDate, 800.0, "Electronics", 101);
//
//        assertEquals(1, item.getItemId());
//        assertEquals("Laptop", item.getItemName());
//        assertEquals("Dell", item.getItemManufacture());
//        assertEquals(expiryDate, item.getItemExpiryDate());
//        assertEquals(800.0, item.getItemPrice());
//        assertEquals("Electronics", item.getItemCategory());
//        assertEquals(101, item.getShopId());
//
//        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
//    }
//
//    @Test
//    void testSetters() {
//        Item item = new Item();
//        LocalDate expiryDate = LocalDate.of(2023, 10, 15);
//
//        item.setItemId(2);
//        item.setItemName("Smartphone");
//        item.setItemManufacture("Samsung");
//        item.setItemExpiryDate(expiryDate);
//        item.setItemPrice(500.0);
//        item.setItemCategory("Electronics");
//        item.setShopId(102);
//
//        assertEquals(2, item.getItemId());
//        assertEquals("Smartphone", item.getItemName());
//        assertEquals("Samsung", item.getItemManufacture());
//        assertEquals(expiryDate, item.getItemExpiryDate());
//        assertEquals(500.0, item.getItemPrice());
//        assertEquals("Electronics", item.getItemCategory());
//        assertEquals(102, item.getShopId());
//
//        System.out.println("testSetters passed successfully.");
//    }
//
//    @Test
//    void testAssertNotNull() {
//        Item item = new Item(1, "Laptop", "Dell", LocalDate.of(2025, 12, 31), 800.0, "Electronics", 101);
//        assertNotNull(item.getItemId());
//        assertNotNull(item.getItemName());
//        System.out.println("testAssertNotNull passed successfully; itemId and itemName are not null.");
//    }
//
//    @Test
//    void testAssertNull() {
//        Item item = new Item(null, null, null, null, null, null, null);
//        assertNull(item.getItemId());
//        assertNull(item.getItemName());
//        assertNull(item.getItemManufacture());
//        assertNull(item.getItemExpiryDate());
//        assertNull(item.getItemPrice());
//        assertNull(item.getItemCategory());
//        assertNull(item.getShopId());
//        System.out.println("testAssertNull passed successfully; all fields are null.");
//    }
//
//    @Test
//    void testAssertFalse() {
//        Item item = new Item(1, "Laptop", "Dell", LocalDate.of(2025, 12, 31), 800.0, "Electronics", 101);
//        assertFalse(item.getItemPrice() == 0);
//        System.out.println("testAssertFalse passed successfully; itemPrice is not zero.");
//    }
//
//    @Test
//    void testAssumeTrue() {
//        Item item = new Item(1, "Laptop", "Dell", LocalDate.of(2025, 12, 31), 800.0, "Electronics", 101);
//        Assumptions.assumeTrue(item.getItemId() == 1);
//        System.out.println("testAssumeTrue passed successfully; assumption is true.");
//    }
//
//    @Test
//    void testAssumingThat() {
//        Item item = new Item(1, "Laptop", "Dell", LocalDate.of(2025, 12, 31), 800.0, "Electronics", 101);
//        Assumptions.assumingThat(item.getItemPrice() > 300, 
//            () -> System.out.println("testAssumingThat passed successfully; itemPrice is greater than 300."));
//    }
//}
