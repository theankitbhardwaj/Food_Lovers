package com.bhardwaj.foodlovers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user_db";
    private static final int DB_VER = 1;
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String MOB = "mob";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
        Log.d("Database operations", "DB Created.");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user (id INTEGER PRIMARY  KEY AUTOINCREMENT,email TEXT, password TEXT, username TEXT, mob TEXT);");
        Log.d("Database Operations", "Table Created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addUser(String username, String password, String email, String mob, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(EMAIL, email);
        contentValues.put(MOB, mob);
        contentValues.put(PASSWORD, password);

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
        Log.d("Database Operations", "One user registered...");
    }

    public boolean checkUser(String email, String password) {
        String[] cols = {ID, USERNAME, MOB};
        SQLiteDatabase db = getReadableDatabase();
        String selection = EMAIL + "=?" + " and " + PASSWORD + "=?";
        String selectionArgs[] = {email, password};
        Cursor cursor = db.query(TABLE_NAME, cols, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserExists(String email, String mob) {
        String[] cols = {ID, USERNAME, MOB};
        SQLiteDatabase db = getReadableDatabase();
        String selection = EMAIL + "=?" + " or " + MOB + "=?";
        String selectionArgs[] = {email, mob};
        Cursor cursor = db.query(TABLE_NAME, cols, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getData(String email) {
        ArrayList<String> data = new ArrayList<>();
        String[] selectionArgs = {email};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "email=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(cursor.getColumnIndex(ID)));
                data.add(cursor.getString(cursor.getColumnIndex(USERNAME)));
                data.add(cursor.getString(cursor.getColumnIndex(EMAIL)));
                data.add(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            } while (cursor.moveToNext());
        }
        return data;
    }
}
