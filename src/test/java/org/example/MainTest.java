package org.example;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
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
}