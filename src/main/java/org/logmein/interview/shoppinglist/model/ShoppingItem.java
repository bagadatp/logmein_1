package org.logmein.interview.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.logmein.interview.shoppinglist.common.Configuration;
import org.logmein.interview.shoppinglist.tools.UniqueInt;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingItem {

    private int id;

    private String name;

    private Integer quantity;

    // In reality we'd have int multiplied by some factor (100)
    // and have a separate JSON and convert between tose two
    private Double price;

    public ShoppingItem() {
        this.id = UniqueInt.getNext();
        this.quantity = 1;
    }

    public ShoppingItem(String name) {
        this.id = UniqueInt.getNext();
        this.name = name;
        this.quantity = 1;
    }

    public ShoppingItem(String name, Double price, Integer quantity) {
        this.id = UniqueInt.getNext();
        if (name != null)
            this.name = name;
        if (price != null)
            this.price = price;
        if (quantity != null)
            this.quantity = quantity;
    }

    public int generateId() {
        this.id = UniqueInt.getNext();
        return this.id;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Double getPrice() {
        return this.price;
    }
    public Integer getQuantity() { return this.quantity; }

    public void incQuantity() {
        this.quantity++;
    }

    public void updateItem(ShoppingItem item) {
        if (item.getName() != null)
            this.name = item.getName();
        if (item.getPrice() != null)
            this.price = item.getPrice();
        if (item.getQuantity() != null)
            this.quantity = item.getQuantity();
    }

    public boolean equals(ShoppingItem item) {
        if (item == null)
            return false;
        return (item.getId() == this.id && item.getName() == this.name && item.getPrice() == this.price && item.getQuantity() == this.quantity);
    }

    private int priceStringToInt(String price) {
        return (int)(Double.parseDouble(price) * (double)Configuration.FLOAT_MULTI_FACTOR);
    }
    private String priceIntToString(int price) {
        double r = (double)price / (double)Configuration.FLOAT_MULTI_FACTOR;
        return String.valueOf(r);
        /*
        int a = price / Configuration.FLOAT_MULTI_FACTOR;
        int b = price % Configuration.FLOAT_MULTI_FACTOR;
        return String.valueOf(a) + "." + String.valueOf(b);
        */
    }
}
