package com.ecommerce;

import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerID;
    private String name;
    private List<Product> shoppingCart;

    public Customer(String customerID, String name) {
        if (customerID == null || customerID.isBlank()) {
            throw new IllegalArgumentException("Customer ID can't be empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name can't be empty.");
        }
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public List<Product> getShoppingCart() { return shoppingCart; }

    public void addToCart(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Can't add a null product to the cart.");
        }
        if (!product.isInStock()) {
            throw new IllegalArgumentException(product.getName() + " is out of stock.");
        }
        shoppingCart.add(product);
    }

    public void removeFromCart(Product product) {
        if (!shoppingCart.remove(product)) {
            throw new IllegalArgumentException("That product isn't in the cart.");
        }
    }

    public double calculateTotalCost() {
        double total = 0;
        for (Product p : shoppingCart) {
            total += p.getPrice();
        }
        return total;
    }

    public Order placeOrder(String orderID) {
        if (shoppingCart.isEmpty()) {
            throw new IllegalStateException("Cart is empty, nothing to order.");
        }
        Order order = new Order(orderID, this, new ArrayList<>(shoppingCart), calculateTotalCost());
        shoppingCart.clear();
        return order;
    }
}
