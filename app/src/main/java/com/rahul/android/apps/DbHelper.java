package com.rahul.android.apps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rspl-rahul on 9/3/18.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/" + MainActivity.class.getPackage().getName() + "/databases/";
    private static final String DATABASE_NAME = "Db";
    private static final int DATABASE_VERSION= 1;
    private SQLiteDatabase myDataBase;
    private Context mycontext;

    public DbHelper(Context context) {

        super(context, DB_PATH + DATABASE_NAME, null, DATABASE_VERSION);
        this.mycontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
