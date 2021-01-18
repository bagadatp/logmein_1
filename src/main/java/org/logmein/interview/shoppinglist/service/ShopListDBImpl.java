package org.logmein.interview.shoppinglist.service;

import org.logmein.interview.shoppinglist.common.ShopListError;
import org.logmein.interview.shoppinglist.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

@Service
public class ShopListDBImpl implements ShopListDB {

    private Map<String, User> users;

    public ShopListDBImpl() {
        this.users = new HashMap<>();
    }

    public User getUser(String userId) {
        return findUser(userId);
    }

    public ShopListError addUser(String userId) {
        User u = findUser(userId);
        if (u != null)
            return ShopListError.USER_ALREADY_EXISTS;
        this.users.put(userId, new User(userId));
        return ShopListError.OK;
    }

    public ShopListError deleteUser(String userId) {
        User u = findUser(userId);
        if (u == null)
            return ShopListError.INVALID_INPUT_USERID;
        this.users.remove(userId);
        return ShopListError.OK;
    }

    private User findUser(String userId) {
        return this.users.get(userId);
    }
}
