package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import exception.EmptyStringException;
import exception.NegativeNumberException;
import model.Product;
import model.Store;
import persistence.JsonReader;
import persistence.JsonWriter;

// Store stock application
// modelled from TellerApp
public class StoreApp {
    private Store store;
    private Product product;
    private ArrayList<Product> pl;
    private static final String JSON_STORE = "./data/store.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Store application
    public StoreApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        // new StoreGUI();
        runStore();
    }

    /*
     * EFFECTS: intializes store
     */
    private void runStore() {
        store = new Store("MegaStore", "Bob");
    }

    /*
     * MODIFIES: this
     * EFFECTS: using the given n, p, and q to add a product to the list of products,
     *          throw NegativeNumberException if p <= 0 or q < 0, and EmptyStringException
     *          if n is empty
     */
    public void doAddProduct(String n, String p, String q) throws EmptyStringException, NegativeNumberException {
        String name = n;
        double price = Double.parseDouble(p);
        int quantity = Integer.parseInt(q);
        if (name.equals("")) {
            throw new EmptyStringException();
        } else if (price <= 0 || quantity < 0) {
            throw new NegativeNumberException();
        } 
        product = new Product(name, price, quantity);
        store.addProduct(product);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes a product indicated by the given index
     */
    public void doRemoveProduct(int index) {
        pl = store.getProducts();
        product = pl.get(index);
        store.removeProduct(product);
    }

    /*
     * EFFECTS: return all products in the store
     */
    public ArrayList<Product> getAllProducts() {
        pl = store.getProducts();
        return pl;
    }

    // EFFECTS: return all products that are in stock
    public ArrayList<Product> getProductsInStock() {
        store.sortStock();
        pl = store.getInStock();
        return pl;
    }

    /*
     * EFFECTS: returns all products that are out of stock
     */
    public ArrayList<Product> getProductsOutOfStock() {
        store.sortStock();
        pl = store.getOutOfStock();
        return pl;
    }

    /*
     * Modelled from JsonSerializationDemo
     * EFFECTS: saves the store to file
     */
    public void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(store);
            jsonWriter.close();
            System.out.println("Saved " + store.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * Modelled from JsonSerializationDemo
     * MODIFIES: this
     * EFFECTS: loads store from file
     */
    public void loadWorkRoom() {
        try {
            store = jsonReader.read();
            System.out.println("Loaded " + store.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
