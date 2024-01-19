package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class introActivity extends AppCompatActivity {

    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout);

        db=new DbHandler(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
            }
        },3000);
    }
    private void openMainActivity() {
       if(db.isRecordsTableExists()==false) {
           Intent intent = new Intent(introActivity.this, MainActivity.class);
           startActivity(intent);
           finish();
       }
       else {
           Intent intent = new Intent(introActivity.this, mpin.class);
           startActivity(intent);
           finish();
       }
    }

}