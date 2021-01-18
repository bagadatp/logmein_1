package org.logmein.interview.shoppinglist.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ShoppingListTest {

    private static final int LIST_ID = 15;
    private static final int ITEM_ID = 3;
    private static final String ITEM_NAME = "vodka";
    private static final String ITEM_NAME2 = "rum";

    @Test
    public void testGetItem_whenEmpty_expectNull() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item = list.getItem(ITEM_ID);
        assertNull(item);
    }

    @Test
    public void testGetItem_whenNotIn_expectNull() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = new ShoppingItem();
        list.getItems().add(item1);
        ShoppingItem item = list.getItem(item1.getId()+1);
        assertNull(item);
    }

    @Test
    public void testGetItem_whenIn_expectMatches() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = new ShoppingItem();
        list.getItems().add(item1);
        ShoppingItem item = list.getItem(item1.getId());
        assertNotNull(item);
        assertEquals(item1, item);
    }

    @Test
    public void testAddItem_whenNull_expectNotAdded() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = null;
        int sizeBefore = list.getItems().size();
        ShoppingItem added = list.addItem(item1);
        int sizeAfter = list.getItems().size();
        assertNull(added);
        assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void testAddItem_whenNotNullButExisting_expectQuantityIncremented() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = new ShoppingItem(ITEM_NAME);
        ShoppingItem item2 = new ShoppingItem(ITEM_NAME);
        int quantityBefore = item1.getQuantity();
        list.addItem(item1);
        int sizeBefore = list.getItems().size();
        ShoppingItem added = list.addItem(item2);
        int sizeAfter = list.getItems().size();
        int quantityAfter = added.getQuantity();
        assertNotNull(added);
        assertEquals(sizeBefore, sizeAfter);
        assertEquals(quantityBefore+1, quantityAfter);
    }

    @Test
    public void testAddItem_whenNotNullNotExisting_expectAdded() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = new ShoppingItem(ITEM_NAME);
        ShoppingItem item2 = new ShoppingItem(ITEM_NAME2);
        list.addItem(item1);
        int sizeBefore = list.getItems().size();
        ShoppingItem added = list.addItem(item2);
        int sizeAfter = list.getItems().size();
        assertNotNull(added);
        assertEquals(sizeBefore+1, sizeAfter);
    }

    @Test
    public void testRemoveItem_whenNotFound_expectNothing() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = new ShoppingItem(ITEM_NAME);
        ShoppingItem item2 = new ShoppingItem(ITEM_NAME2);
        list.addItem(item1);
        int sizeBefore = list.getItems().size();
        ShoppingItem removed = list.removeItem(item2.getId());
        int sizeAfter = list.getItems().size();
        assertNull(removed);
        assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void testRemoveItem_whenFound_expectRemoved() {
        ShoppingList list = new ShoppingList(LIST_ID);
        ShoppingItem item1 = new ShoppingItem(ITEM_NAME);
        ShoppingItem item2 = new ShoppingItem(ITEM_NAME2);
        list.addItem(item1);
        list.addItem(item2);
        int sizeBefore = list.getItems().size();
        ShoppingItem removed = list.removeItem(item2.getId());
        int sizeAfter = list.getItems().size();
        assertNotNull(removed);
        assertEquals(sizeBefore-1, sizeAfter);
    }

}
