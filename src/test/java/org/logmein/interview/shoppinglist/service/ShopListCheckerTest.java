package org.logmein.interview.shoppinglist.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopListCheckerTest {

    private static final String USER1 = "user1";
    private static final String USER2 = "user2";
    private static final String USER3 = "user3";
    private static final String USER4 = "user4";
    private static final int LIST1 = 99;


    @Test
    public void testUserExists_whenEmpty_expectFalse() {
        ShopListDB db = new ShopListDBImpl();
        ShopListChecker checker = new ShopListCheckerImpl(db);

        boolean res = checker.userExists(USER1);
        assertFalse(res);
    }

    @Test
    public void testUserExists_whenExists_expectTrue() {
        ShopListDB db = new ShopListDBImpl();
        ShopListChecker checker = new ShopListCheckerImpl(db);
        db.addUser(USER1);
        boolean res = checker.userExists(USER1);
        assertTrue(res);
    }

    @Test
    public void testUserExists_whenNotExists_expectFalse() {
        ShopListDB db = new ShopListDBImpl();
        ShopListChecker checker = new ShopListCheckerImpl(db);
        db.addUser(USER1);
        db.addUser(USER2);
        db.addUser(USER3);
        boolean res = checker.userExists(USER4);
        assertFalse(res);
    }

    @Test
    public void testListExists_whenUserNotExists_expectFalse() {
        ShopListDB db = new ShopListDBImpl();
        ShopListChecker checker = new ShopListCheckerImpl(db);
        boolean res = checker.listExists(USER1, LIST1);
        assertFalse(res);
    }

    @Test
    public void testListExists_whenUserExistsButListNotExists_expectFalse() {
        ShopListDB db = new ShopListDBImpl();
        ShopListChecker checker = new ShopListCheckerImpl(db);
        db.addUser(USER1);
        boolean res = checker.listExists(USER1, LIST1);
        assertFalse(res);
    }

    @Test
    public void testListExists_whenUserExistsAndListExists_expectTrue() {
        ShopListDB db = new ShopListDBImpl();
        ShopListChecker checker = new ShopListCheckerImpl(db);
        db.addUser(USER1);
        db.getUser(USER1).addShoppingList(LIST1);
        boolean res = checker.listExists(USER1, LIST1);
        assertTrue(res);
    }


}
