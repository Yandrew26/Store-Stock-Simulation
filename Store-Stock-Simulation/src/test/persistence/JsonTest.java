package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Product;

public class JsonTest {
    protected void checkProduct(String name, Double price, int quantity, Product product) {
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
    }
}
