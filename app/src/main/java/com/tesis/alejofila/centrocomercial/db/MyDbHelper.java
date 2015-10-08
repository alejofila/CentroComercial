package com.tesis.alejofila.centrocomercial.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alejandro on 08/10/2015.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    /**
     * Constants
     */
    public static final int DATABASE_VERSION = 0;
    public static final String DATABASE_NAME = "db.sqlite3";
    public static final String TAG = MyDbHelper.class.getSimpleName();

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating database");

        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
