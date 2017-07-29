package com.android.mounika.to_do;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mounika on 26/07/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, "Mydatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Mydatatable(KEY_TITLE  TEXT,KEY_DESCRIPTION TEXT,KEY_DATE  TEXT, KEY_STATUS INTEGER)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
