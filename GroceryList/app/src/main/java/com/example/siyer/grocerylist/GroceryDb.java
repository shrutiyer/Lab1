package com.example.siyer.grocerylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by siyer on 9/22/2015.
 */
public final class GroceryDb extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Grocery.db";
    private SQLiteDatabase writableGroceryDb;
    private SQLiteDatabase readableGroceryDb;

    public GroceryDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        writableGroceryDb = getWritableDatabase();
        readableGroceryDb = getReadableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Schema.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Schema.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean writeToDatabase(String groceryItem) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(Schema.COLUMN_NAME, groceryItem);

        try {
            writableGroceryDb.insertOrThrow(Schema.TABLE_NAME, null, insertValues);
            return true;
        } catch (Exception ex) {
            Log.e("Database Service", "Error occurred inserting into database", ex);
            return false;
        }
    }

    public ArrayList<String> readDb() {
        String[] columnsToQuery = {
                Schema.COLUMN_NAME,
                Schema._ID
        };
        Cursor groceryCursor = readableGroceryDb.query(Schema.TABLE_NAME, columnsToQuery, null, null, null, null, null);
        ArrayList<String> groceryList = new ArrayList<String>();
        if (groceryCursor.moveToFirst()) {
            groceryList.add(groceryCursor.getString(0));
            while (groceryCursor.moveToNext()) {
                groceryList.add(groceryCursor.getString(0));
            }
        }
        return groceryList;
    }
}
