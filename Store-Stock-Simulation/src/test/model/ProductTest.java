package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// testing methods in the Product class
public class ProductTest {
    private Product testProduct1;

    @BeforeEach
    void runBefore() {
        testProduct1 = new Product("Milk", 4.99, 100);
    }

    @Test
    void testConstructor() {
        assertEquals("Milk", testProduct1.getName());
        assertEquals(4.99, testProduct1.getPrice());
        assertEquals(100, testProduct1.getQuantity());
    }

    @Test
    void testStockStatus() {
        assertTrue(testProduct1.stockStatus());
        testProduct1.setQuantity(0);
        assertFalse(testProduct1.stockStatus());
    }
}
