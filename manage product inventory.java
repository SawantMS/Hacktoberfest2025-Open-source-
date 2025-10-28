//To manager product inventory

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Product class representing an item in the inventory
class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity (as it can change)
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Price: $" + String.format("%.2f", price) + ", Quantity: " + quantity;
    }
}

// InventoryManager class to handle inventory operations
class InventoryManager {
    private Map<String, Product> inventory; // Stores products by their ID

    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists. Use update to change quantity.");
        } else {
            inventory.put(product.getProductId(), product);
            System.out.println("Product " + product.getName() + " added successfully.");
        }
    }

    public void updateProductQuantity(String productId, int newQuantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            System.out.println("Quantity for " + product.getName() + " updated to " + newQuantity + ".");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
        System.out.println("-------------------------\n");
    }
}

public class InventoryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        // Adding initial products
        manager.addProduct(new Product("P001", "Laptop", 1200.00, 10));
        manager.addProduct(new Product("P002", "Mouse", 25.50, 50));
        manager.addProduct(new Product("P003", "Keyboard", 75.00, 30));

        manager.displayInventory();

        // User interaction loop
        while (true) {
            System.out.println("Options: 1. Add Product, 2. Update Quantity, 3. Display Inventory, 4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Product Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    manager.addProduct(new Product(id, name, price, quantity));
                    break;
                case 2:
                    System.out.print("Enter Product ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    manager.updateProductQuantity(updateId, newQuantity);
                    break;
                case 3:
                    manager.displayInventory();
                    break;
                case 4:
                    System.out.println("Exiting Inventory App.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
