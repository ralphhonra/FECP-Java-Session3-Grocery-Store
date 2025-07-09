package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static HashMap<String, Integer> items = new HashMap<>();
    static Scanner input = new Scanner(System.in);
    static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        int choiceInput = 0;

        while (choiceInput != 6) {
            userPromptDisplay();

            choiceInput = Integer.parseInt(input.nextLine());
            switch (choiceInput) {
                case 1 -> viewInventory();
                case 2 -> addProduct();
                case 3 -> checkProduct();
                case 4 -> updateProduct();
                case 5 -> removeProduct();
                case 6 -> System.out.println("Exiting system...");
            }
        }
    }

    static void viewInventory() {
        inventory.viewInventory(items);
    }

    static void addProduct() {
        System.out.print("Enter product name: ");
        String productName = input.nextLine();
        int productQuantity = verifyNumber();

        HashMap<String, Integer> item = new HashMap<>();
        item.put(productName, productQuantity);

        System.out.println(inventory.addProduct(item));
    }

    static void checkProduct() {
        System.out.print("Enter product name to check: ");
        String productToSearch = input.nextLine();

        inventory.checkProduct(productToSearch);
    }

    static void updateProduct() {
        System.out.print("Enter product name to update: ");
        String productToUpdate = input.nextLine();
        System.out.print("Enter new stock quantity: ");
        int newProductQuantity = verifyNumber();

        HashMap<String, Integer> itemUpdate = new HashMap<>();
        itemUpdate.put(productToUpdate, newProductQuantity);
        System.out.println(inventory.updateProduct(itemUpdate));
    }

    static void removeProduct() {
        System.out.print("Enter product name to remove: ");
        String productToRemove = input.nextLine();

        inventory.removeProduct(productToRemove);
    }

    static int verifyNumber() {
        int productQuantity = 0;
        while (true) {
            try {
                System.out.print("Enter quantity: ");
                productQuantity = Integer.parseInt(input.nextLine());

                if (productQuantity <= 0) System.out.println("\nInput cannot be a zero or negative number\n");
                else break;
            } catch (Exception err) {
                System.out.println("\nInvalid Input\n");
            }
        }
        return productQuantity;
    }

    private static void userPromptDisplay() {
        System.out.println();
        System.out.println("--- Grocery Inventory Menu ---");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Product");
        System.out.println("3. Check Product");
        System.out.println("4. Update Product");
        System.out.println("5. Remove Product");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
}

class Inventory extends Main implements InventoryFunctions {

    @Override
    public String addProduct(HashMap<String, Integer> productInformation) {
        Map.Entry<String, Integer> addedProduct = productInformation.entrySet().iterator().next();

        //  For test case only
        int quantity = addedProduct.getValue();
        if (quantity <= 0) {
            System.out.println("Input cannot be a zero or negative number");
            return "";
        }
        //

        if (items.containsKey(addedProduct.getKey())) return "Product already exists.";
        else {
            items.put(addedProduct.getKey(), addedProduct.getValue());
            return "Product added!";
        }
    }

    @Override
    public void checkProduct(String productToSearch) {
        items.entrySet().stream()
                .filter(s -> Objects.equals(s.getKey().toLowerCase(), productToSearch.toLowerCase()))
                .forEach(s -> System.out.println(s.getKey() + " is in stock" + ": " + s.getValue()));
    }

    @Override
    public String updateProduct(HashMap<String, Integer> productInformation) {
        Map.Entry<String, Integer> item = productInformation.entrySet().iterator().next();
        items.replace(item.getKey(), item.getValue());

        return "Stock updated!";
    }

    @Override
    public String removeProduct(String itemToRemove) {
        items.remove(itemToRemove);

        return "Product removed.";
    }

    @Override
    public void viewInventory(HashMap<String, Integer> productInformation) {
        System.out.println();
        System.out.println("Current Inventory:");
        items.entrySet()
                .stream()
                .forEach(item -> System.out.println(item.getKey() + " - " + item.getValue()));
    }
}

interface InventoryFunctions {
    String addProduct(HashMap<String, Integer> productInformation);
    void checkProduct(String productName);
    String updateProduct(HashMap<String, Integer> productInformation);
    String removeProduct(String itemToRemove);
    void viewInventory(HashMap<String, Integer> productInformation);
}