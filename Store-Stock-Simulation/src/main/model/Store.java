package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a store having a name, owner name, list of products, 
// list of in stock products and list of out of stock products
public class Store implements Writable {
    private String name;                    // Store name
    private String owner;                   // owner name
    private ArrayList<Product> products;    // list of products 
    private ArrayList<Product> inStock;     // list of in stock products
    private ArrayList<Product> outOfStock;  // list of out of stock products

    /*
     * REQUIRES: name and owner have a non-zero length
     * EFFECTS: constructs a store with given name and owner with a empty Arraylist of products, inStock, and outOfStock
     */
    public Store(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.products = new ArrayList<Product>();
        this.inStock = new ArrayList<Product>();
        this.outOfStock = new ArrayList<Product>();
    }

    // getters
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getInStock() {
        return inStock;
    }

    public ArrayList<Product> getOutOfStock() {
        return outOfStock;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setInStock(ArrayList<Product> inStock) {
        this.inStock = inStock;
    }

    public void setOutOfStock(ArrayList<Product> outOfStock) {
        this.outOfStock = outOfStock;
    }

    /*
     * REQUIRES: the given list of products not empty, empty inStock, and empty outOfStock
     * MODIFIES: this
     * EFFECTS: sort the list of products into a list of in stock products and
     *          out of stock products
     */
    public void sortStock() {
        inStock = new ArrayList<Product>();
        outOfStock = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            boolean status = currentProduct.stockStatus();
            if (status) {
                inStock.add(currentProduct);
            } else {
                outOfStock.add(currentProduct);
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: add the given product into list of products
     */
    public void addProduct(Product product) {
        products.add(product);
        EventLog.getInstance().logEvent(new Event("Added " + product.getName() + " to " + name));
    }
    
    /*
     * REQUIRES: the given product must be in the list of products, products not empty
     * MODIFIES: this
     * EFFECTS: add the given product into list of products
     */
    public void removeProduct(Product product) {
        products.remove(product);
        EventLog.getInstance().logEvent(new Event("Removed " + product.getName() + " from " + name));
    }

    // EFFECTS: returns number of products in this productroom
    public int numProducts() {
        return products.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("store_name", name);
        json.put("user", owner);
        json.put("products", productsToJson());
        return json;
    }

    // EFFECTS: returns products in this productroom as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product p : products) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
