package org.logmein.interview.shoppinglist.common;

public enum ShopListError {
    OK(0, "OK"),
    INVALID_INPUT_USERID(-1, "Invalid user ID provided"),
    USER_ALREADY_EXISTS(-2, "User already exists"),
    INVALID_INPUT_LISTID(-3, "Invalid shopping list ID provided"),
    LIST_ALREADY_EXISTS(-4, "Shopping list already exists"),
    INVALID_INPUT_ITEMID(-5, "Invalid shopping item ID provided"),
    ITEM_ALREADY_EXISTS(-6, "Shopping item already exists"),
    INVALID_INPUT_ITEM_DATA(-7, "Invalid item data provided"),
    INVALID_INPUT_JSON(-8, "Invalid input Json payload"),
    SYSTEM_ERROR(-100, "System error"),
    UNKNOWN_ERROR(-1000, "Unknown error");

    private final int code;
    private final String description;

    private ShopListError(int code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public int value() { return this.code; }

    public String getDescription() { return this.description; }

    public String toString() {
        return this.code + " " + this.name();
    }

    public static ShopListError valueOf(int code) {
        ShopListError status = resolve(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        } else {
            return status;
        }
    }

    public static ShopListError resolve(int code) {
        ShopListError[] valList = ShopListError.values();
        for (ShopListError e : valList) {
            if (code == e.code)
                return e;
        }
        return null;
    }
}
