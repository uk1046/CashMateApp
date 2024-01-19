package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    DbHandler db;
    ImageButton b1,b2,b3,b4,b05,b01,b02,b03,b04;

    TextView t1,t2,t3,t4,t5,uname,email001;
    String userEmail;

    Intent i1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        b1 = (ImageButton) findViewById(R.id.btn001);
        b2 = (ImageButton) findViewById(R.id.btn002);
        b3 = (ImageButton) findViewById(R.id.btn003);
        b4 = (ImageButton) findViewById(R.id.btn004);
        b05 = (ImageButton) findViewById(R.id.ic05);

        b01 = (ImageButton) findViewById(R.id.ic01);
        t1 = (TextView)findViewById (R.id.txt101);

        t5 = (TextView)findViewById (R.id.txt105);

        email001=findViewById(R.id.email);
        uname=findViewById(R.id.username);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , Profile.class);
                startActivity(i1);
                finish();
            }
        });
        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , Profile.class);
                startActivity(i1);
                finish();
            }
        });


        b02 = (ImageButton) findViewById(R.id.ic02);
        t2= (TextView)findViewById (R.id.txt102);

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , Security.class);
                startActivity(i1);
                finish();
            }
        });
        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , Security.class);
                startActivity(i1);
                finish();
            }
        });


        b03 = (ImageButton) findViewById(R.id.ic03);
        t3= (TextView)findViewById (R.id.txt103);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , Help.class);
                startActivity(i1);
                finish();
            }
        });
        b03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , Help.class);
                startActivity(i1);
                finish();
            }
        });
        b04 = (ImageButton) findViewById(R.id.ic04);
        t4= (TextView)findViewById (R.id.txt104);

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , About.class);
                startActivity(i1);
                finish();
            }
        });
        b04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Settings.this , About.class);
                startActivity(i1);
                finish();
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Settings.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Settings.this, InterFace.class);
                startActivity(i1);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Settings.this, Investment.class);
                startActivity(i1);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Settings.this, Insurance.class);
                startActivity(i1);
                finish();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        b05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Settings.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        db=new DbHandler(this);
        userEmail = getSharedPreferences("user_prefs", MODE_PRIVATE)
                .getString("user_email", "Email not available");
        uname.setText(db.getFirstName(userEmail)+" "+db.getLastName(userEmail));
        email001.setText(userEmail);
    }

}
