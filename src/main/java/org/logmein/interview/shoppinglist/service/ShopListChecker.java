package org.logmein.interview.shoppinglist.service;

import org.logmein.interview.shoppinglist.model.ShoppingItem;

public interface ShopListChecker {

    boolean userExists(String userId);

    boolean listExists(String userId, int listId);

    boolean itemExists(String userId, int listId, int itemId);

    boolean itemValid(ShoppingItem item);
}
