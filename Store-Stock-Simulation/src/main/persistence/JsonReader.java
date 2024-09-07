package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Product;
import model.Store;

// Modelled from JsonSerializationDemo 
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads store from file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public Store read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses store from JSON object and returns it
    private Store parseStore(JSONObject jsonObject) {
        String name = jsonObject.getString("store_name");
        String user = jsonObject.getString("user");
        Store s = new Store(name, user);
        addProducts(s, jsonObject);
        return s;
    }

    /*
     * MODIFIES: s
     * EFFECTS: parses products from JSON object and adds them to store
     */
    private void addProducts(Store s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(s, nextProduct);
        }
    }

    /*
     * MODIFIES: s
     * EFFECTS: parses product from JSON object and adds it to store
     */
    private void addProduct(Store s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        int quantity = jsonObject.getInt("quantity");
        Product product = new Product(name, price, quantity);
        s.addProduct(product);
    }
}
