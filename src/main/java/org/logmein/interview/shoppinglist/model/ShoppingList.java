package org.logmein.interview.shoppinglist.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingList {

    private int id;
    private List<ShoppingItem> items;

    public ShoppingList(int id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public int getId() { return this.id; }
    public List<ShoppingItem> getItems() { return this.items; }

    public ShoppingItem getItem(int itemId) {
        return findShoppingItemById(itemId);
    }

    public ShoppingItem addItem(ShoppingItem item) {
        if (item == null)
            return null;
        ShoppingItem item2 = findShoppingItemByName(item.getName());
        if (item2 != null) {
            item2.incQuantity();
            return item2;
        }
        item.generateId();
        this.items.add(item);
        return item;
    }

    public ShoppingItem removeItem(int itemId) {
        ShoppingItem item = findShoppingItemById(itemId);
        if (item == null)
            return null;
        this.items.remove(item);
        return item;
    }

    private ShoppingItem findShoppingItemById(int itemId) {
        if (this.items == null)
            return null;
        for (ShoppingItem item : this.items) {
            if (item.getId() == itemId)
                return item;
        }
        return null;
    }

    private ShoppingItem findShoppingItemByName(String name) {
        if (name == null || this.items == null)
            return null;
        for (ShoppingItem item : this.items) {
            if (StringUtils.equalsIgnoreCase(name, item.getName()))
                return item;
        }
        return null;
    }
}
