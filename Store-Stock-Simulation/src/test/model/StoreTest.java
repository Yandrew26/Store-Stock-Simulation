package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// testing methods in the Store class
public class StoreTest {
    private Store testStore1;
    private Product product1;
    private Product product2;
    private Product product3;
    private ArrayList<Product> list;
    
    @BeforeEach
    void runBefore() {
        testStore1 = new Store("Superstore", "Andrew");
        product1 = new Product("Candy", 1.99, 0);
        product2 = new Product("Coke", 2.99, 90);
        product3 = new Product("Cookies", 6.99, 56);
        list = new ArrayList<Product>();
        list.add(product1);
        list.add(product2);
        list.add(product3);
    }

    @Test
    void testConstructor() {
        assertEquals("Superstore", testStore1.getName());
        assertEquals("Andrew", testStore1.getOwner());
        assertTrue(testStore1.getProducts().isEmpty());
        assertTrue(testStore1.getInStock().isEmpty());
        assertTrue(testStore1.getOutOfStock().isEmpty());
    }

    @Test
    void testSortStock() {
        ArrayList<Product> list1 = new ArrayList<Product>();
        ArrayList<Product> list2 = new ArrayList<Product>();
        list1.add(product2);
        list1.add(product3);
        list2.add(product1);
        testStore1.addProduct(product1);
        testStore1.addProduct(product2);
        testStore1.addProduct(product3);
        testStore1.sortStock();
        assertEquals(list1, testStore1.getInStock());
        assertEquals(list2, testStore1.getOutOfStock());
    }

    @Test
    void testAddProduct() {
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(product1);
        testStore1.addProduct(product1);
        assertEquals(list, testStore1.getProducts());
    }

    @Test
    void testMultipleAddProduct() {
        testStore1.addProduct(product1);
        testStore1.addProduct(product2);
        testStore1.addProduct(product3);
        assertEquals(list, testStore1.getProducts());
    }

    @Test
    void testRemoveProduct() {
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(product3);
        list.add(product2);
        testStore1.addProduct(product1);
        testStore1.addProduct(product3);
        testStore1.addProduct(product2);
        testStore1.removeProduct(product1);
        assertEquals(list, testStore1.getProducts());
    }
    
    @Test
    void testMultipleRemoveProduct() {
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(product2);
        testStore1.addProduct(product1);
        testStore1.addProduct(product3);
        testStore1.addProduct(product2);
        testStore1.removeProduct(product1);
        testStore1.removeProduct(product3);
        assertEquals(list, testStore1.getProducts());
    }
}
