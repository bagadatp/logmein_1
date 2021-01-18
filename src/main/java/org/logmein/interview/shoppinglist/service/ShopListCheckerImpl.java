package org.logmein.interview.shoppinglist.service;

import org.apache.commons.lang3.StringUtils;
import org.logmein.interview.shoppinglist.model.ShoppingItem;
import org.logmein.interview.shoppinglist.model.ShoppingList;
import org.logmein.interview.shoppinglist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopListCheckerImpl implements ShopListChecker {

    private ShopListDB db;

    @Autowired
    public ShopListCheckerImpl(ShopListDB db) {
        this.db = db;
    }

    public boolean userExists(String userId) {
        User user = db.getUser(userId);
        return (user != null);
    }

    public boolean listExists(String userId, int listId) {
        User user = db.getUser(userId);
        if (user == null)
            return false;
        ShoppingList list = user.getShoppingList(listId);
        return (list != null);
    }

    public boolean itemExists(String userId, int listId, int itemId) {
        User user = db.getUser(userId);
        if (user == null)
            return false;
        ShoppingList list = user.getShoppingList(listId);
        if (list == null)
            return false;
        ShoppingItem item = list.getItem(itemId);
        return (item != null);
    }

    public boolean itemValid(ShoppingItem item) {
        if (item == null)
            return false;
        if (StringUtils.isBlank(item.getName()))
            return false;
        if (item.getQuantity() <= 0)
            return false;
        return true;
    }
}
