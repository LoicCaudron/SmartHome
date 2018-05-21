package com.example.einore.smarthome.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserBDD {    //classe DAO

    private static final int VERSION = 1;
    private static final String NOM_BDD = "user.db";

    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_PIN = "PIN";
    private static final int NUM_COL_PIN = 2;
    private static final String COL_PHONE = "PHONE";
    private static final int NUM_COL_PHONE = 3;
    private static final String COL_T_MIN = "T_MIN";
    private static final int NUM_COL_T_MIN = 4;
    private static final String COL_T_MAX = "T_MAX";
    private static final int NUM_COL_T_MAX = 5;
    private static final String COL_HUMIDITY_MIN = "HUMIDITY_MIN";
    private static final int NUM_COL_HUMIDITY_MIN = 6;
    private static final String COL_HUMIDITY_MAX = "HUMIDITY_MAX";
    private static final int NUM_COL_HUMIDITY_MAX = 7;
    private static final String COL_BATTERY_MIN = "BATTERY_MIN";
    private static final int NUM_COL_BATTERY_MIN =8;
    private static final String COL_BATTERY_MAX = "BATTERY_MAX";
    private static final int NUM_COL_BATTERY_MAX = 9;


    private SQLiteDatabase bdd;

    private UserBaseSQLite users;

    public UserBDD(Context context) {
        users = new UserBaseSQLite(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = users.getWritableDatabase();
    }

    public void openForRead() {
        bdd = users.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public long insertUser(User user) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, user.getName());
        content.put (COL_PIN, user.getPin());

        return bdd.insert(TABLE_USERS, null, content);
    }

    public int updateUser(int id, User user) {
        ContentValues content = new ContentValues();
        content.put(COL_PHONE, user.getPhone());
        content.put(COL_T_MIN, user.getT_min());
        content.put(COL_T_MAX, user.getT_max());
        content.put(COL_HUMIDITY_MIN, user.getHumidity_min());
        content.put(COL_HUMIDITY_MAX, user.getHumidity_max());
        content.put(COL_BATTERY_MIN, user.getBattery_min());
        content.put(COL_BATTERY_MAX, user.getBattery_max());

        return bdd.update(TABLE_USERS, content, COL_ID + " = " + user.getId(), null);
    }

    public int removeUser(String name) {
        return bdd.delete(TABLE_USERS, COL_NAME + " = " + name, null);
    }


    public User getUser(int id) {
        Cursor c = bdd.query(TABLE_USERS,
                new String[] { COL_ID, COL_NAME, COL_PIN, COL_PHONE, COL_T_MIN, COL_T_MAX, COL_HUMIDITY_MIN, COL_HUMIDITY_MAX, COL_BATTERY_MIN, COL_BATTERY_MAX},
                COL_ID + " = " + id,
                null,
                null,
                null,
                null);
        return cursorToUser(c);
    }

    public User getUser(String name, int pin) {
        Cursor c = bdd.query(TABLE_USERS, new String[] { COL_ID, COL_NAME, COL_PIN, COL_PHONE, COL_T_MIN, COL_T_MAX, COL_HUMIDITY_MIN, COL_HUMIDITY_MAX, COL_BATTERY_MIN, COL_BATTERY_MAX },
                COL_NAME + " LIKE \"" + name + "\" AND " + COL_PIN + " = " + pin, null, null,null, COL_NAME);
        return cursorToUser(c);
    }

    public User cursorToUser(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        User user = new User(c.getString(NUM_COL_NAME), c.getInt(NUM_COL_PIN));
        user.setId(c.getInt(NUM_COL_ID));
        user.setPhone(c.getString(NUM_COL_PHONE));
        user.setT_max(c.getInt(NUM_COL_T_MAX));
        user.setT_min(c.getInt(NUM_COL_T_MIN));
        user.setHumidity_min(c.getInt(NUM_COL_HUMIDITY_MIN));
        user.setHumidity_max(c.getInt(NUM_COL_HUMIDITY_MAX));
        user.setBattery_min(c.getInt(NUM_COL_BATTERY_MIN));
        user.setBattery_max(c.getInt(NUM_COL_BATTERY_MAX));

        c.close();
        return user;
    }

    public ArrayList<User> getAllUsers() {
        Cursor c = bdd.query(TABLE_USERS, new String[] { COL_ID, COL_NAME, COL_PIN, COL_PHONE, COL_T_MIN, COL_T_MAX, COL_HUMIDITY_MIN, COL_HUMIDITY_MAX, COL_BATTERY_MIN, COL_BATTERY_MAX },
                null, null, null, null, COL_NAME);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<User> userList = new ArrayList<User> ();
        c.moveToFirst();
        do {
            User user = new User(c.getString(NUM_COL_NAME), c.getInt(NUM_COL_PIN));
            user.setId(c.getInt(NUM_COL_ID));
            user.setPhone(c.getString(NUM_COL_PHONE));
            user.setT_max(c.getInt(NUM_COL_T_MAX));
            user.setT_min(c.getInt(NUM_COL_T_MIN));
            user.setHumidity_min(c.getInt(NUM_COL_HUMIDITY_MIN));
            user.setHumidity_max(c.getInt(NUM_COL_HUMIDITY_MAX));
            user.setBattery_min(c.getInt(NUM_COL_BATTERY_MIN));
            user.setBattery_max(c.getInt(NUM_COL_BATTERY_MAX));

            userList.add(user);

        }while (c.moveToNext());
        //c.close();
        return userList;
    }
}
