package com.tesis.alejofila.centrocomercial.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alejandro on 08/10/2015.
 */
public class
        MyDbHelper extends SQLiteOpenHelper {
    /**
     * Constants
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db.sqlite3";
    public static final String TAG = MyDbHelper.class.getSimpleName();

    private static MyDbHelper instance;

    public static synchronized MyDbHelper getInstance(Context context){
        if(instance == null)
            instance = new MyDbHelper(context.getApplicationContext());
        return instance;

    }

    private MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating database");
        db.execSQL(TablesDB.Producto.CREATE_TABLE);
        db.execSQL(TablesDB.Oferta.CREATE_TABLE);

    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
