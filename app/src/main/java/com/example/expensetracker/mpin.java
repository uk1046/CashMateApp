package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class mpin extends AppCompatActivity {

    DbHandler db;
    TextView pass;
    String userEmail;
    String str = "", str2 = "";
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,del,ent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin);

        b0=findViewById(R.id.no0);
        b1=findViewById(R.id.no1);
        b2=findViewById(R.id.no2);
        b3=findViewById(R.id.no3);
        b4=findViewById(R.id.no4);
        b5=findViewById(R.id.no5);
        b6=findViewById(R.id.no6);
        b7=findViewById(R.id.no7);
        b8=findViewById(R.id.no8);
        b9=findViewById(R.id.no9);
        del=findViewById(R.id.del);
        ent=findViewById(R.id.go);

        pass=findViewById(R.id.password);
        db=new DbHandler(this);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="0";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="1";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="2";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="3";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="4";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="5";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="6";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="7";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="8";
                str2+="* ";
                pass.setText(str2);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str+="9";
                str2+="* ";
                pass.setText(str2);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str="";
                str2="";
                pass.setText(str2);
            }
        });
        ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.getMPIN(userEmail).equals(str)) {
                    Intent i = new Intent(mpin.this, InterFace.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(mpin.this, "Invalid M-PIN !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        userEmail = getSharedPreferences("user_prefs", MODE_PRIVATE)
                .getString("user_email", "Email not available");

    }
}