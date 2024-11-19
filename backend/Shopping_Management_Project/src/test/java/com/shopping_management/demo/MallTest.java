package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import com.shopping_management.demo.model.Mall;

class MallTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        Mall mall = new Mall(1, "Grand Plaza", 101, "Downtown", "Shopping");

        assertEquals(1, mall.getMallId());
        assertEquals("Grand Plaza", mall.getMallName());
        assertEquals(101, mall.getMallAdminId());
        assertEquals("Downtown", mall.getMallLocation());
        assertEquals("Shopping", mall.getMallCategory());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        Mall mall = new Mall();
        
        mall.setMallId(2);
        mall.setMallName("City Mall");
        mall.setMallAdminId(102);
        mall.setMallLocation("Uptown");
        mall.setMallCategory("Entertainment");

        assertEquals(2, mall.getMallId());
        assertEquals("City Mall", mall.getMallName());
        assertEquals(102, mall.getMallAdminId());
        assertEquals("Uptown", mall.getMallLocation());
        assertEquals("Entertainment", mall.getMallCategory());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        Mall mall = new Mall(1, "Grand Plaza", 101, "Downtown", "Shopping");
        assertNotNull(mall.getMallId());
        assertNotNull(mall.getMallName());
        assertNotNull(mall.getMallAdminId());
        assertNotNull(mall.getMallLocation());
        assertNotNull(mall.getMallCategory());

        System.out.println("testAssertNotNull passed successfully; none of the fields are null.");
    }

    @Test
    void testAssertNull() {
        Mall mall = new Mall();
        assertNull(mall.getMallId());
        assertNull(mall.getMallName());
        assertNull(mall.getMallAdminId());
        assertNull(mall.getMallLocation());
        assertNull(mall.getMallCategory());

        System.out.println("testAssertNull passed successfully; all fields are null.");
    }

    @Test
    void testAssertFalse() {
        Mall mall = new Mall(1, "Grand Plaza", 101, "Downtown", "Shopping");
        assertFalse(mall.getMallId() == 0);
        System.out.println("testAssertFalse passed successfully; mallId is not zero.");
    }

    @Test
    void testAssumeTrue() {
        Mall mall = new Mall(1, "Grand Plaza", 101, "Downtown", "Shopping");
        Assumptions.assumeTrue(mall.getMallId() == 1);
        System.out.println("testAssumeTrue passed successfully; assumption is true.");
    }

    @Test
    void testAssumingThat() {
        Mall mall = new Mall(1, "Grand Plaza", 101, "Downtown", "Shopping");
        Assumptions.assumingThat(mall.getMallLocation() != null, 
            () -> System.out.println("testAssumingThat passed successfully; mallLocation is not null."));
    }

    @Test
    void testToString() {
        Mall mall = new Mall(1, "Grand Plaza", 101, "Downtown", "Shopping");
        String expected = "Mall [mallId=1, mallName=Grand Plaza, mallAdminId=101, mallLocation=Downtown, mallCategory=Shopping]";
        assertEquals(expected, mall.toString());

        System.out.println("testToString passed successfully.");
    }
}
