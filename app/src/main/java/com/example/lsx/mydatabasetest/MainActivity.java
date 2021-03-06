package com.example.lsx.mydatabasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lsx.mydatabasetest.Database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private Button mCreateButton;
    private Button mAddButton;
    private Button mUpdateButton;
    private Button mDeteleButton;
    private Button mQueryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,3);
        mCreateButton= (Button) findViewById(R.id.activity_main_create_button);
        mAddButton=(Button)findViewById(R.id.activity_main_add_button);
        mUpdateButton= (Button) findViewById(R.id.activity_main_update_button);
        mDeteleButton= (Button) findViewById(R.id.activity_main_delete_button);
        mQueryButton= (Button) findViewById(R.id.activity_main_query_button);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //开始组装第一条数据
                values.put("name","The Da Vinci Dode");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);//插入第一条数据
                values.clear();
                //开始组装第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);//插入第一条数据
            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 9.99);
                db.update("Book", values,"name=?", new String[]{"The Da Vinci Dode"});
            }
        });
        mDeteleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.delete("Book","pages>?",new String[]{"500"});
            }
        });

        mQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                //查询Book表中的所有的数据
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并打印
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","book name is " + name);
                        Log.d("MainActivity","book author is " + author);
                        Log.d("MainActivity","book pages is " + pages);
                        Log.d("MainActivity","book price is " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
