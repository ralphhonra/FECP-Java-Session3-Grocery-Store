package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void addProductWithValidIntegerQuantity() {
        String expectedKey = "Melon";
        int expectedValue = 30;

        HashMap<String, Integer> itemToAdd = new HashMap<>();
        itemToAdd.put(expectedKey, expectedValue);

        inventory.addProduct(itemToAdd);
        int actualValue = Main.items.get(expectedKey);

        assertNotNull(Main.items);
        assertEquals(expectedValue, actualValue);
    }

//    @Test
//    void checkProduct() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void updateProduct() {
//    }
//
//    @org.junit.jupiter.api.Test
//    void removeProduct() {
//    }
}