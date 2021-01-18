package org.logmein.interview.shoppinglist.service;

import org.logmein.interview.shoppinglist.common.ShopListError;
import org.logmein.interview.shoppinglist.model.User;

public interface ShopListDB {

    User getUser(String userId);

    ShopListError addUser(String userId);

    ShopListError deleteUser(String userId);

}
