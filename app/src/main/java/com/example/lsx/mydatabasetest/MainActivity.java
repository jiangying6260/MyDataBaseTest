package com.example.lsx.mydatabasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lsx.mydatabasetest.Database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private Button mCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        mCreateButton= (Button) findViewById(R.id.activity_main_create_button);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

    }
}
