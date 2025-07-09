package org.example;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Inventory inventory;
    private HashMap<String, Integer> hashmapEntry;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        hashmapEntry = new HashMap<>();
        System.setOut(new PrintStream(outputStream));
        Main.items = new HashMap<>();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void addProductWithValidIntegerQuantity() {
        String expectedKey = "Melon";
        int expectedValue = 30;

        hashmapEntry.put(expectedKey, expectedValue);
        inventory.addProduct(hashmapEntry);
        int actualValue = Main.items.get(expectedKey);

        assertNotNull(Main.items);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void addProductWithZeroQuantity() {
        String expectedKey = "Mango";
        int expectedValue = 0;
        String expectedConsoleOutput = "Input cannot be a zero or negative number";

        hashmapEntry.put(expectedKey, expectedValue);
        inventory.addProduct(hashmapEntry);

        String output = outputStream.toString();

        assertTrue(output.contains(expectedConsoleOutput));
    }

    @Test
    void addProductThatAlreadyExist() {
        String testKey = "Milk";
        int testValue = 30;

        hashmapEntry.put(testKey, testValue);
        inventory.addProduct(hashmapEntry);

        int newValue = 20;
        HashMap<String, Integer> newEntry = new HashMap<>();
        newEntry.put(testKey, newValue);

        String result = inventory.addProduct(newEntry);
        String expectedConsoleOutput = "Product already exists.";

        assertEquals(expectedConsoleOutput, result);
    }

    @Test
    void updateProductWithSameProductWithDifferentQuantity() {
        String testKey = "Milk";
        int testValue = 30;
        int newValue = 50;

        hashmapEntry.put(testKey, testValue);
        inventory.addProduct(hashmapEntry);

        HashMap<String, Integer> newEntry = new HashMap<>();
        newEntry.put(testKey, newValue);

        String result = inventory.updateProduct(newEntry);
        String expectedConsoleOutput = "Stock updated!";

        int inputtedValue = Main.items.get(testKey);
        assertEquals(expectedConsoleOutput, result);
        assertEquals(newValue, inputtedValue);
    }

    @Test
    void checkProductExistingProduct() {
        String testKey = "Milk";
        int testQuantity = 20;

        hashmapEntry.put(testKey, testQuantity);
        inventory.addProduct(hashmapEntry);
        inventory.checkProduct(testKey);

        String output = outputStream.toString();
        String expectedOutput = testKey + " is in stock: " + testQuantity;

        assertTrue(output.contains(expectedOutput));
    }

    @Test
    void checkProductThatDoesNotExist() {
        String testKey = "Milk";
        int testQuantity = 20;
        String newKey = "Ice Cream";

        hashmapEntry.put(testKey, testQuantity);
        inventory.addProduct(hashmapEntry);
        inventory.checkProduct(newKey);

        String output = outputStream.toString();
        String expectedOutput = "Product not found.";

        assertTrue(output.contains(expectedOutput));
    }

    @Test
    void updateProductWithValidQuantity() {
        String testKey = "Bread";
        int testQuantity = 20;
        int newQuantity = 25;

        hashmapEntry.put(testKey, testQuantity);
        inventory.addProduct(hashmapEntry);

        HashMap<String, Integer> newEntry = new HashMap<>();
        newEntry.put(testKey, newQuantity);
        inventory.updateProduct(newEntry);

        int checkNewAmount = Main.items.get(testKey);
        assertEquals(newQuantity, checkNewAmount);
    }

    @Test
    void updateProductNonExistingProduct() {
        String testKey = "Tofu";
        int testAmount = 20;

        hashmapEntry.put(testKey, testAmount);
        String result = inventory.updateProduct(hashmapEntry);
        String expectedOutput = "Product not found.";

        assertTrue(result.contains(expectedOutput));
    }


    @Test
    void removeProductRemoveValidEntry() {
        String testKey = "Milk";
        int testQuantity = 20;

        hashmapEntry.put(testKey, testQuantity);
        inventory.addProduct(hashmapEntry);

        String expectedOutput = "Product removed.";
        String result = inventory.removeProduct(testKey);

        assertTrue(result.contains(expectedOutput));
    }
}