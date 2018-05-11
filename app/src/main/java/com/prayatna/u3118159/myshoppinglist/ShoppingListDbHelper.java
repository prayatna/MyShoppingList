package com.prayatna.u3118159.myshoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prayatna on 18/03/16.
 */
public class ShoppingListDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "shopping_list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_LOCATION = "location";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_QTY = "quantity";



    public ShoppingListDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        "(" + COLUMN_ID + " integer primary key, " +
                        COLUMN_ITEM_NAME + " text, " +
                        COLUMN_ITEM_LOCATION + " text, " +
                        COLUMN_NOTES + " text, " +
                        COLUMN_QTY + " integer )"
        );

        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME + "( " + COLUMN_ITEM_NAME + "," + COLUMN_ITEM_LOCATION +
                "," + COLUMN_NOTES + "," + COLUMN_QTY + ") VALUES('iPhone','JB-HiFi','Buy new',2),('Pillow','K-Mart','fluffy',3)" +
                ",('Carrots','Coles','1bunch',1),('Shoes','DFO','size 8',1),('Guitar Strings','BetterMusic','all6 aucostic strings',6);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);

    }


    //not used--> can get specific Item with an id
    public Item getShoppingItem(int pId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(TABLE_NAME, new String[] {COLUMN_ID, COLUMN_ITEM_NAME, COLUMN_ITEM_LOCATION,
                COLUMN_NOTES, COLUMN_QTY}, "id=?", new String[] {Integer.toString(pId)}, null, null, null);
        res.moveToFirst();
        Item item = null;
        if (res.moveToFirst()) {
            item = createItemFromCursor(res);
        }

        if (res != null && !res.isClosed()) {
            res.close();
        }
        return item;
    }


    public void addItem(Item item) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = createContentValuesFromItem(item);
            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("ShoppingListDbHelper", e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    //getting all items from db
    public List<Item> getAllItems() {
        List<Item> itemsAll = new ArrayList<>();


        String ITEMS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ITEMS_SELECT_QUERY, null);

            while (cursor.moveToNext()) {
                Item newItem = createItemFromCursor(cursor);

                itemsAll.add(newItem);
            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

        db.close();

        return itemsAll;
    }

    // Deleting single item
    public void deleteItem(Item item) {
        deleteItem(item.getId());
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                    new String[] { Integer.toString(id) });
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("ShoppingListDbHelper", e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    //update an item
    public void updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = createContentValuesFromItem(item);

            db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{Integer.toString(item.getId())});

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("ShoppingListDbHelper", e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    private Item createItemFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        String itemName = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME));
        String itemLocation = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_LOCATION));
        String itemNotes = cursor.getString(cursor.getColumnIndex(COLUMN_NOTES));
        int itemQty = cursor.getInt(cursor.getColumnIndex(COLUMN_QTY));

        return new Item(id, itemName, itemQty, itemLocation, itemNotes);
    }

    private ContentValues createContentValuesFromItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, item.getItemName());
        values.put(COLUMN_ITEM_LOCATION, item.getItemLocation());
        values.put(COLUMN_NOTES, item.getItemNotes());
        values.put(COLUMN_QTY, item.getItemQty());
        Log.d("Shopping_Content", "Item quantity: " + item.getItemQty());
        return values;
    }

}
