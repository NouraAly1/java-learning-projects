import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // a small starting catalog
        Product[] catalog = {
            new Product("P001", "Wireless Mouse", 19.99, 50),
            new Product("P002", "Mechanical Keyboard", 89.50, 20),
            new Product("P003", "USB-C Hub", 34.75, 15),
            new Product("P004", "Laptop Stand", 27.00, 30)
        };

        System.out.println("=== Welcome to the Store ===");
        System.out.println("Here's what we have in stock:\n");
        for (Product p : catalog) {
            System.out.println(p);
        }

        Customer customer = new Customer("C001", "Jamie Rivera");
        System.out.println("\nLogged in as: " + customer.getName());

        System.out.println("\nEnter a product ID to add to your cart, or type 'done' to stop:");
        while (true) {
            System.out.print("> ");
            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("done")) {
                break;
            }

            Product selected = findProductById(catalog, choice);
            if (selected == null) {
                System.out.println("That product ID doesn't exist. Try again.");
                continue;
            }

            try {
                customer.addToCart(selected);
                System.out.println(selected.getName() + " added to your cart.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Couldn't add that item: " + ex.getMessage());
            }
        }

        System.out.println("\n=== Your Cart ===");
        if (customer.getShoppingCart().isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product p : customer.getShoppingCart()) {
                System.out.printf("%s - $%.2f%n", p.getName(), p.getPrice());
            }
        }
        System.out.printf("Total: $%.2f%n", customer.calculateTotalCost());

        System.out.println("\nPlacing order...");
        try {
            Order order = customer.placeOrder("ORD1001");
            System.out.println("Order placed successfully!\n");
            System.out.println(order.generateOrderSummary());

            order.updateStatus("SHIPPED");
            System.out.println("\nStatus updated. New summary:");
            System.out.println(order.generateOrderSummary());

        } catch (IllegalStateException ex) {
            System.out.println("Could not place order: " + ex.getMessage());
        }

        input.close();
    }

    private static Product findProductById(Product[] catalog, String id) {
        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}
