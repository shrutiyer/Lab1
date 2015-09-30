package com.example.siyer.grocerylist;

import android.provider.BaseColumns;

/**
 * Created by siyer on 9/22/2015.
 */
public class Schema implements BaseColumns {
    public static final String TABLE_NAME = "grocery";
    public static final String COLUMN_NAME = "gitem";


    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    " )";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
