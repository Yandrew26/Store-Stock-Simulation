package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a product having a name, price, and quantity
public class Product implements Writable {
    private String name;
    private double price;
    private int quantity;

    /*
     * REQUIRES: name has a non-zero length, price > 0, quantity >= 0
     * EFFECTS: constructs a product with given id, name, price, and quantity
     */
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // setters
    // public void setName(String name) {
    //     this.name = name;
    // }

    // public void setPrice(double price) {
    //     this.price = price;
    // }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // // EFFECTS: returns string representation of this product
    // public String toString() {
    //     return "Product [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    // }

    // REQUIRES: quantity >= 0
    // EFFECTS: if quantity > 0 then return true, otherwise return false
    public boolean stockStatus() {
        if (quantity > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("quantity", quantity);
        return json;
    }
}
