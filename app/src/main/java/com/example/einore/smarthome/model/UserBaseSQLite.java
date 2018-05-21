package com.example.einore.smarthome.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBaseSQLite extends SQLiteOpenHelper{

    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_PIN = "PIN";
    private static final String COL_PHONE = "PHONE";
    private static final String COL_T_MIN = "T_MIN";
    private static final String COL_T_MAX = "T_MAX";
    private static final String COL_HUMIDITY_MIN = "HUMIDITY_MIN";
    private static final String COL_HUMIDITY_MAX = "HUMIDITY_MAX";
    private static final String COL_BATTERY_MIN = "BATTERY_MIN";
    private static final String COL_BATTERY_MAX = "BATTERY_MAX";


    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_USERS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT NOT NULL, " +
            COL_PIN + " INTEGER NOT NULL, " +
            COL_PHONE + " TEXT, " +
            COL_T_MIN + " FLOAT, " +
            COL_T_MAX + " FLOAT, " +
            COL_HUMIDITY_MIN + " FLOAT, " +
            COL_HUMIDITY_MAX + " FLOAT, " +
            COL_BATTERY_MIN + " FLOAT, " +
            COL_BATTERY_MAX + " FLOAT);";

    public UserBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_USERS);
        onCreate(db);
    }
}
