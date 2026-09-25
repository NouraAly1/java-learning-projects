package com.ecommerce;

public class Product {
    private String productID;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String productID, String name, double price, int stockQuantity) {
        if (productID == null || productID.isBlank()) {
            throw new IllegalArgumentException("Product ID can't be empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name can't be empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative.");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock can't be negative.");
        }

        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price can't be negative.");
        this.price = price;
    }

    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) throw new IllegalArgumentException("Stock can't be negative.");
        this.stockQuantity = stockQuantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%.2f (%d in stock)", productID, name, price, stockQuantity);
    }
}