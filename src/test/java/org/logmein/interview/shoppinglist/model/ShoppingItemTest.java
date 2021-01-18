package org.logmein.interview.shoppinglist.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingItemTest {

    @Test
    public void testConstructor3_whenAllNonNull_expectMatches() {
        String name = "apple";
        Double price = 2.35;
        Integer quantity = 2;
        ShoppingItem item = new ShoppingItem(name, price, quantity);
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    public void testIncQuantity_expectIncremented() {
        String name = "apple";
        Double price = 2.35;
        Integer quantity = 2;
        ShoppingItem item = new ShoppingItem(name, price, quantity);
        item.incQuantity();
        assertEquals(quantity+1, item.getQuantity());
    }

    @Test
    public void testupdateItem_whenNull_expectNotUpdated() {
        String name = "apple";
        Double price = 2.35;
        Integer quantity = 2;
        ShoppingItem item = new ShoppingItem(name, price, quantity);
        ShoppingItem item2 = new ShoppingItem();
        item.updateItem(item2);
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(item2.getQuantity(), item.getQuantity());
    }

    @Test
    public void testupdateItem_whenNotNull_expectUpdated() {
        String name = "apple";
        Double price = 2.35;
        Integer quantity = 2;
        String name2 = "pear";
        Double price2 = price*2;
        Integer quantity2 = quantity+3;
        ShoppingItem item = new ShoppingItem(name, price, quantity);
        ShoppingItem item2 = new ShoppingItem(name2, price2, quantity2);
        item.updateItem(item2);
        assertEquals(name2, item.getName());
        assertEquals(price2, item.getPrice());
        assertEquals(quantity2, item.getQuantity());
    }
}
