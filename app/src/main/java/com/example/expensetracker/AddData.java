package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddData extends AppCompatActivity {
    DbHandler db;
    ImageButton btn;
    Button bn01;
    RadioGroup rg1, rg2;
    EditText et1, et2;
    RadioButton r1, r2, r3, r4, r5;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        btn = findViewById(R.id.back);
        bn01 = findViewById(R.id.add);
        rg1 = findViewById(R.id.input01);
        rg2 = findViewById(R.id.input02);
        et1 = findViewById(R.id.amt);
        et2 = findViewById(R.id.desc01);

        r1 = findViewById(R.id.rb1);
        r2 = findViewById(R.id.rb2);
        r3 = findViewById(R.id.rb3);
        r4 = findViewById(R.id.rb4);
        r5 = findViewById(R.id.rb5);


        db = new DbHandler(this);
       r1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(AddData.this , InterFace.class);
                startActivity(i1);
                finish();
            }
        });

        bn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((r1.isChecked() || r2.isChecked()) && (r3.isChecked() || r4.isChecked() || r5.isChecked()) && !et1.getText().toString().isEmpty()) {
                    int selectedRadioButtonId1 = rg1.getCheckedRadioButtonId();
                    String selectedText1 = getSelectedRadioButtonText(selectedRadioButtonId1);

                    String note=et2.getText().toString();
                    int selectedRadioButtonId2 = rg2.getCheckedRadioButtonId();
                    String selectedText2 = getSelectedRadioButtonText(selectedRadioButtonId2);

                    int amount = Integer.parseInt(et1.getText().toString());

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                    String currentDate = dateFormat.format(calendar.getTime());
                    String currentTime = timeFormat.format(calendar.getTime());

                    Intent i=new Intent(AddData.this,InterFace.class);
                    db.insertRecord(selectedText1,selectedText2,amount,currentDate,currentTime,note);
                    Toast.makeText(AddData.this, "Record inserted", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(AddData.this, "All Fields Are Required", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private String getSelectedRadioButtonText(int radioButtonId) {
        if (radioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(radioButtonId);
            return selectedRadioButton.getText().toString();
        }
        return ""; // Return an empty string if no RadioButton is selected
    }
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}