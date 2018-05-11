package com.prayatna.u3118159.myshoppinglist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by prayatna on 24/03/16.
 */
public class Item implements Serializable {

    private final int id;
    private final String itemName;
    private final int itemQty;
    private final String itemLocation;
    private final String itemNotes;

    public Item(int id, String itemName, int itemQty, String itemLocation, String itemNotes) {
        this.id = id;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemLocation = itemLocation;
        this.itemNotes = itemNotes;
    }

    public int getId() { return id; }

    public String getItemName() {
        return itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, itemName: %s, " +
                "itemQty: %d, itemLocation: %s, itemNotes: %s}", id, itemName, itemQty, itemLocation, itemNotes);
    }
}
