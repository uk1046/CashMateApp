package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Insurance extends AppCompatActivity {

    ImageButton b01,b02,b03,b04,pb01,pb02,pb03,pb04,pb05,pb06,pb07,pb08;
    TextView tv01,tv02,tv03,tv04,tv05,tv06,tv07,tv08;
    Intent i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        b01 = (ImageButton) findViewById(R.id.btn001);
        b02 = (ImageButton) findViewById(R.id.btn002);
        b03 = (ImageButton) findViewById(R.id.btn003);
        b04 = (ImageButton) findViewById(R.id.btn004);
        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, InterFace.class);
                startActivity(i1);
                finish();
            }
        });
        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, Investment.class);
                startActivity(i1);
                finish();
            }
        });
        b03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        b04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, Settings.class);
                startActivity(i1);
                finish();
            }
        });

        pb01 = (ImageButton) findViewById(R.id.bikeimg);
        pb01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb1.class);
                startActivity(i1);
                finish();
            }
        });
        tv01 = (TextView) findViewById(R.id.biketxt);
        tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb1.class);
                startActivity(i1);
                finish();
            }
        });


        pb02 = (ImageButton) findViewById(R.id.carimg01);
        pb02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb2.class);
                startActivity(i1);
                finish();
            }
        });
        tv02 = (TextView) findViewById(R.id.cartxt1);
        tv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb2.class);
                startActivity(i1);
                finish();
            }
        });
        pb03 = (ImageButton) findViewById(R.id.img01);
        pb03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb3.class);
                startActivity(i1);
                finish();
            }
        });
        tv03 = (TextView) findViewById(R.id.txt001);
        tv03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb3.class);
                startActivity(i1);
                finish();
            }
        });

        pb04 = (ImageButton) findViewById(R.id.img001);
        pb04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb4.class);
                startActivity(i1);
                finish();
            }
        });
        tv04 = (TextView) findViewById(R.id.text1);
        tv04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb4.class);
                startActivity(i1);
                finish();
            }
        });

        pb05 = (ImageButton) findViewById(R.id.ic01);
        pb05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb5.class);
                startActivity(i1);
                finish();
            }
        });
        tv05 = (TextView) findViewById(R.id.travel);
        tv05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb5.class);
                startActivity(i1);
                finish();
            }
        });

        pb06 = (ImageButton) findViewById(R.id.ic02);
        pb06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb6.class);
                startActivity(i1);
                finish();
            }
        });
        tv06 = (TextView) findViewById(R.id.accident);
        tv06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb6.class);
                startActivity(i1);
                finish();
            }
        });

        pb07 = (ImageButton) findViewById(R.id.ic03);
        pb07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb7.class);
                startActivity(i1);
                finish();
            }
        });
        tv07 = (TextView) findViewById(R.id.topup);
        tv07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb7.class);
                startActivity(i1);
                finish();
            }
        });

        pb08 = (ImageButton) findViewById(R.id.ic04);
        pb08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb8.class);
                startActivity(i1);
                finish();
            }
        });
        tv08 = (TextView) findViewById(R.id.shop);
        tv08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = new Intent(Insurance.this, pb8.class);
                startActivity(i1);
                finish();
            }
        });

    }
}