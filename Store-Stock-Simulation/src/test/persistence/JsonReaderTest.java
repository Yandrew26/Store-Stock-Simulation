package persistence;

import model.Product;
import model.Store;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Store s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStore.json");
        try {
            Store s = reader.read();
            assertEquals("MegaStore", s.getName());
            assertEquals("Bob", s.getOwner());
            assertEquals(0, s.numProducts());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStore.json");
        try {
            Store s = reader.read();
            assertEquals("MegaStore", s.getName());
            assertEquals("Bob", s.getOwner());
            List<Product> products = s.getProducts();
            assertEquals(2, products.size());
            checkProduct("Milk", 4.99, 123, products.get(0));
            checkProduct("Candy", 0.99, 987, products.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
