package com.example.lsx.mydatabasetest.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.database.sqlite.SQLiteDatabase.*;

/**
 * Created by lsx on 2016/7/31.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_BOOK="create table Book ("
            + "id integer primary key autoincrement ,"
            + "author text ,"
            + "price real ,"
            + "pages integer ,"
            + "name text)";

    public static final String CREATE_GATEGORY="create table Category ("
            + "id integer primary key autoincrement,"
            + "category_neme text,"
            + "categor_code integer)";


    private Context mContext;

    public MyDatabaseHelper(Context context,String name,CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_GATEGORY);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Gategory");
        onCreate(db);

    }
}
