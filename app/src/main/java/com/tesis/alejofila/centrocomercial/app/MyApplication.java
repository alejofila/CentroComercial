package com.tesis.alejofila.centrocomercial.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;




/**
 * Created by alejofila on 5/09/15.
 */
public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();



    public void onCreate() {
        super.onCreate();
        initParse();
    }
    private void initParse(){
        Parse.initialize(this, AppConfig.PARSE_APPLICATION_ID, AppConfig.PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
