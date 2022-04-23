package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private final static String DB_NAME = "StepN";
    private final static int DB_VERSION = 1;
    private static Database instance;

    public static synchronized Database getInstance(final Context c) {
        if (instance == null) {
            instance = new Database(c.getApplicationContext());
        }
        openCounter.incrementAndGet();
        return instance;
    }
}
