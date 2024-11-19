package com.shopping_management.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import com.shopping_management.demo.model.MallAdmin;

class MallAdminTest {

    @Test
    void testParameterizedConstructorAndGetters() {
        MallAdmin mallAdmin = new MallAdmin(1, 1001);

        assertEquals(1, mallAdmin.getMallAdminId());
        assertEquals(1001, mallAdmin.getMallId());

        System.out.println("testParameterizedConstructorAndGetters passed successfully.");
    }

    @Test
    void testSetters() {
        MallAdmin mallAdmin = new MallAdmin();
        
        mallAdmin.setMallAdminId(2);
        mallAdmin.setMallId(1002);

        assertEquals(2, mallAdmin.getMallAdminId());
        assertEquals(1002, mallAdmin.getMallId());

        System.out.println("testSetters passed successfully.");
    }

    @Test
    void testAssertNotNull() {
        MallAdmin mallAdmin = new MallAdmin(1, 1001);
        assertNotNull(mallAdmin.getMallAdminId());
        assertNotNull(mallAdmin.getMallId());

        System.out.println("testAssertNotNull passed successfully; fields are not null.");
    }

    @Test
    void testAssertNull() {
        MallAdmin mallAdmin = new MallAdmin();
        assertNull(mallAdmin.getMallAdminId());
        assertNull(mallAdmin.getMallId());

        System.out.println("testAssertNull passed successfully; all fields are null.");
    }

    @Test
    void testAssertFalse() {
        MallAdmin mallAdmin = new MallAdmin(1, 1001);
        assertFalse(mallAdmin.getMallId() == 0);
        System.out.println("testAssertFalse passed successfully; mallId is not zero.");
    }

    @Test
    void testAssumeTrue() {
        MallAdmin mallAdmin = new MallAdmin(1, 1001);
        Assumptions.assumeTrue(mallAdmin.getMallAdminId() == 1);
        System.out.println("testAssumeTrue passed successfully; assumption is true.");
    }

    @Test
    void testAssumingThat() {
        MallAdmin mallAdmin = new MallAdmin(1, 1001);
        Assumptions.assumingThat(mallAdmin.getMallAdminId() > 0, 
            () -> System.out.println("testAssumingThat passed successfully; mallAdminId is greater than 0."));
    }

    @Test
    void testToString() {
        MallAdmin mallAdmin = new MallAdmin(1, 1001);
        String expected = "MallAdmin [mallAdminId=1, mallId=1001]";
        assertEquals(expected, mallAdmin.toString());

        System.out.println("testToString passed successfully.");
    }
}
