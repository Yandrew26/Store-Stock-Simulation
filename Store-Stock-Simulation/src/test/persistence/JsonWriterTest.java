package persistence;

import model.Store;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Store s = new Store("MegaStore", "Bob");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Store s = new Store("MegaStore", "Bob");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStore.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStore.json");
            s = reader.read();
            assertEquals("MegaStore", s.getName());
            assertEquals("Bob", s.getOwner());
            assertEquals(0, s.numProducts());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Store s = new Store("MegaStore", "Bob");
            s.addProduct(new Product("Cola", 1.99, 1000));
            s.addProduct(new Product("Chips", 2.50, 500));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStore.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStore.json");
            s = reader.read();
            assertEquals("MegaStore", s.getName());
            assertEquals("Bob", s.getOwner());
            List<Product> products = s.getProducts();
            assertEquals(2, products.size());
            checkProduct("Cola", 1.99, 1000, products.get(0));
            checkProduct("Chips", 2.50, 500, products.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
