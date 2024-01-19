package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Investment extends AppCompatActivity {

        ImageButton b1,b2,b3,b4,ig1,ig2,ig3,ig4,ig5,ig6,ig7,ig8;
        Button btn1;
        RelativeLayout r1,r2,r3,r4;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_investment);
            b1 = (ImageButton) findViewById(R.id.btn001);
            b2 = (ImageButton) findViewById(R.id.btn002);
            b3 = (ImageButton) findViewById(R.id.btn003);
            b4 = (ImageButton) findViewById(R.id.btn004);
            btn1 = (Button) findViewById(R.id.sip01);


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(Investment.this, InterFace.class);
                    startActivity(i2);
                    finish();
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent i2 = new Intent(Investment.this, Insurance.class);
                    startActivity(i2);
                    finish();
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(Investment.this, Settings.class);
                    startActivity(i2);
                    finish();
                }
            });

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , SIP.class);
                    startActivity(i1);
                    finish();
                }
            });


            ig1 = (ImageButton) findViewById(R.id.idea1);
            ig1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , Gold.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig2 = (ImageButton) findViewById(R.id.idea2);
            ig2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , company.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig3 = (ImageButton) findViewById(R.id.idea3);
            ig3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , taxSaving.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig4 = (ImageButton) findViewById(R.id.idea4);
            ig4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , start100.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig5 = (ImageButton) findViewById(R.id.idea5);
            ig5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , bestSip.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig6 = (ImageButton) findViewById(R.id.idea6);
            ig6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , funds.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig7 = (ImageButton) findViewById(R.id.idea7);
            ig7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , trending.class);
                    startActivity(i1);
                    finish();
                }
            });

            ig8 = (ImageButton) findViewById(R.id.idea8);
            ig8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , highReturn.class);
                    startActivity(i1);
                    finish();
                }
            });

            r1 = (RelativeLayout) findViewById(R.id.fund01);
            r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , google1.class);
                    startActivity(i1);
                    finish();
                }
            });

            r2 = (RelativeLayout) findViewById(R.id.fund02);
            r2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , google2.class);
                    startActivity(i1);
                    finish();
                }
            });

            r3 = (RelativeLayout) findViewById(R.id.fund03);
            r3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , google3.class);
                    startActivity(i1);
                    finish();
                }
            });

            r4 = (RelativeLayout) findViewById(R.id.fund04);
            r4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(Investment.this , google4.class);
                    startActivity(i1);
                    finish();
                }
            });

        }
}