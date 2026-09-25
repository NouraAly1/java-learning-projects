package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.List;

public class Order {
    private String orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private String status;

    public Order(String orderID, Customer customer, List<Product> products, double orderTotal) {
        if (orderID == null || orderID.isBlank()) {
            throw new IllegalArgumentException("Order ID can't be empty.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("An order needs a customer.");
        }
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("An order needs at least one product.");
        }

        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.orderTotal = orderTotal;
        this.status = "PENDING";
    }

    public String getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }
    public double getOrderTotal() { return orderTotal; }
    public String getStatus() { return status; }

    public void updateStatus(String newStatus) {
        List<String> validStatuses = List.of("PENDING", "SHIPPED", "DELIVERED", "CANCELLED");
        if (newStatus == null || !validStatuses.contains(newStatus.toUpperCase())) {
            throw new IllegalArgumentException("Not a valid status: " + newStatus);
        }
        this.status = newStatus.toUpperCase();
    }

    public String generateOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Items:\n");
        for (Product p : products) {
            sb.append(String.format("  - %s ($%.2f)%n", p.getName(), p.getPrice()));
        }
        sb.append(String.format("Order Total: $%.2f", orderTotal));
        return sb.toString();
    }
}
