package org.logmein.interview.shoppinglist.controller;

import org.logmein.interview.shoppinglist.common.ShopListError;
import org.logmein.interview.shoppinglist.service.ShopListChecker;
import org.logmein.interview.shoppinglist.service.ShopListDB;
import org.logmein.interview.shoppinglist.model.ShoppingItem;
import org.logmein.interview.shoppinglist.model.ShoppingList;
import org.logmein.interview.shoppinglist.model.User;
import org.logmein.interview.shoppinglist.service.ShopListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoplist")
public class ShopListController {

    Logger logger = LoggerFactory.getLogger(ShopListController.class);

    private ShopListDB db;

    private ShopListChecker checker;

    @Autowired
    public ShopListController(ShopListDB db, ShopListChecker checker) {
        this.db = db;
        this.checker = checker;
    }

    ///////////////////////////////////////////////////////////////////////
    // Endpoints for read-only actions - getList(s)
    @GetMapping(value = "/{uid}")
    public ResponseEntity<ShoppingList> getAllLists(@PathVariable("uid") String userId) {
        logger.debug("=> getAllLists({})", userId);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }

        User user = db.getUser(userId);
        List<ShoppingList> lists = user.getShoppingLists();
        logger.debug("<= getAllLists({}) : done", userId);
        return ShopListResponse.createResponseOk(lists);
    }

    @GetMapping(value = "/{uid}/{listId}")
    public ResponseEntity<ShoppingList> getList(@PathVariable("uid") String userId,
                                         @PathVariable("listId") int listId) {
        logger.debug("=> getList({}, {})", userId, listId);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }
        if (!checker.listExists(userId, listId)) {
            logger.error("List id {} does not exist", listId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_LISTID);
        }

        User user = db.getUser(userId);
        ShoppingList list = user.getShoppingList(listId);
        logger.debug("<= getList({}, {}) : done", userId, listId);
        return ShopListResponse.createResponseOk(list);
    }


    ///////////////////////////////////////////////////////////////////////
    // Endpoints for manipulation of user - addUser, deleteUser
    @PostMapping(value = "/user/{uid}")
    public ResponseEntity addUser(@PathVariable("uid") String userId) {
        logger.debug("=> addUser({})", userId);

        if (checker.userExists(userId)) {
            logger.error("User {} already exists", userId);
            return ShopListResponse.createResponseErr(ShopListError.USER_ALREADY_EXISTS);
        }

        db.addUser(userId);
        logger.debug("<= addUser({}) : done", userId);
        return ShopListResponse.createNilResponseOk();
    }

    @DeleteMapping(value = "/user/{uid}")
    public ResponseEntity deleteUser(@PathVariable("uid") String userId) {
        logger.debug("=> deleteUser({})", userId);

        if (!checker.userExists(userId)) {
            logger.error("User {} doesn't exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }

        db.deleteUser(userId);
        logger.debug("<= deleteUser({}): done", userId);
        return ShopListResponse.createNilResponseOk();
    }


    ///////////////////////////////////////////////////////////////////////
    // Endpoints for manipulation of shopping lists for a particular user
    @PostMapping(value = "/list/{uid}/{listId}")
    public ResponseEntity startList(@PathVariable("uid") String userId,
                                     @PathVariable("listId") int listId) {
        logger.debug("=> createList({}, {})", userId, listId);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }
        if (checker.listExists(userId, listId)) {
            logger.error("List id {} already exists", listId);
            return ShopListResponse.createResponseErr(ShopListError.LIST_ALREADY_EXISTS);
        }

        User user = db.getUser(userId);
        user.addShoppingList(listId);
        logger.debug("<= createList({}, {}): done", userId, listId);
        return ShopListResponse.createNilResponseOk();
    }

    @DeleteMapping(value = "/list/{uid}/{listId}")
    public ResponseEntity deleteList(@PathVariable("uid") String userId,
                                     @PathVariable("listId") int listId) {
        logger.debug("=> deleteList({}, {})", userId, listId);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }
        if (!checker.listExists(userId, listId)) {
            logger.error("List id {} doesn't exists", listId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_LISTID);
        }

        User user = db.getUser(userId);
        user.removeShoppingList(listId);
        logger.debug("<= deleteList({}, {}): done", userId, listId);
        return ShopListResponse.createNilResponseOk();
    }


    ///////////////////////////////////////////////////////////////////////
    // Endpoints for manipulation of shopping items within lists
    @PostMapping(value = "/item/{uid}/{listId}")
    public ResponseEntity addItem(@PathVariable("uid") String userId,
                                  @PathVariable("listId") int listId,
                                  @RequestBody ShoppingItem item) {
        logger.debug("=> addItem({}, {}, {})", userId, listId, item);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }
        if (!checker.listExists(userId, listId)) {
            logger.error("List id {} doesn't exists", listId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_LISTID);
        }
        if (!checker.itemValid(item)) {
            logger.error("Item provided is invalid: {}", item);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_ITEM_DATA);
        }

        User u = db.getUser(userId);
        ShoppingList list = u.getShoppingList(listId);
        ShoppingItem itemAdded = list.addItem(item);
        logger.debug("<= addItem({}, {}, {}): done", userId, listId, item);
        return ShopListResponse.createResponseOk(itemAdded);
    }

    @PutMapping(value = "/item/{uid}/{listId}/{itemId}")
    public ResponseEntity changeItem(@PathVariable("uid") String userId,
                                     @PathVariable("listId") int listId,
                                     @PathVariable("itemId") int itemId,
                                     @RequestBody ShoppingItem item) {
        logger.debug("=> changeItem({}, {}, {}, {})", userId, listId, itemId, item);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }
        if (!checker.listExists(userId, listId)) {
            logger.error("List id {} doesn't exists", listId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_LISTID);
        }
        if (!checker.itemExists(userId, listId, itemId)) {
            logger.error("Item id {} doesn't exists", itemId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_ITEMID);
        }
        if (!checker.itemValid(item)) {
            logger.error("Item provided is invalid: {}", item);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_ITEM_DATA);
        }

        User u = db.getUser(userId);
        ShoppingList list = u.getShoppingList(listId);
        ShoppingItem item2Update = list.getItem(itemId);
        item2Update.updateItem(item);
        logger.debug("<= changeItem({}, {}, {}, {}): done", userId, listId, itemId, item);
        return ShopListResponse.createResponseOk(item2Update);
    }

    @DeleteMapping(value = "/item/{uid}/{listId}/{itemId}")
    public ResponseEntity deleteItem(@PathVariable("uid") String userId,
                                     @PathVariable("listId") int listId,
                                     @PathVariable("itemId") int itemId) {
        logger.debug("=> deleteItem({}, {}, {})", userId, listId, itemId);

        if (!checker.userExists(userId)) {
            logger.error("User {} does not exist", userId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_USERID);
        }
        if (!checker.listExists(userId, listId)) {
            logger.error("List id {} doesn't exists", listId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_LISTID);
        }
        if (!checker.itemExists(userId, listId, itemId)) {
            logger.error("Item id {} doesn't exists", itemId);
            return ShopListResponse.createResponseErr(ShopListError.INVALID_INPUT_ITEMID);
        }

        User u = db.getUser(userId);
        ShoppingList list = u.getShoppingList(listId);
        ShoppingItem itemRemoved = list.removeItem(itemId);
        logger.debug("<= deleteItem({}, {}, {}): done", userId, listId, itemId);
        return ShopListResponse.createResponseOk(itemRemoved);
    }
}
