package org.logmein.interview.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private String id;
    private List<ShoppingList> shoppingLists;

    public User(String id) {
        this.id = id;
        this.shoppingLists = new ArrayList<>();
    }

    public String getId() { return this.id; }

    public List<ShoppingList> getShoppingLists() {
        return this.shoppingLists;
    }

    public void addShoppingList(int listId) {
        if (findShoppingList(listId) == null)
            this.shoppingLists.add(new ShoppingList(listId));
    }

    public void removeShoppingList(int listId) {
        int i = findShoppingListIndex(listId);
        if (i < 0)
            return;
        this.shoppingLists.remove(i);
    }

    public ShoppingList getShoppingList(int listId) {
        return findShoppingList(listId);
    }

    private ShoppingList findShoppingList(int listId) {
        if (this.shoppingLists == null)
            return null;
        for (ShoppingList l : this.shoppingLists) {
            if (l.getId() == listId)
                return l;
        }
        return null;
    }

    private int findShoppingListIndex(int listId) {
        if (this.shoppingLists == null)
            return -1;
        for (int i = 0; i < this.shoppingLists.size(); i++) {
            if (this.shoppingLists.get(i).getId() == listId)
                return i;
        }
        return -1;
    }
}
