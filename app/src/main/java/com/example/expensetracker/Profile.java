package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
   ImageButton i01;
    Button btn;

    EditText et1,et2,et3,et4;
    DbHandler db;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        i01 = (ImageButton) findViewById(R.id.back);
        btn =(Button) findViewById(R.id.save);

        et1=findViewById(R.id.edit01);
        et2=findViewById(R.id.edit02);
        et3=findViewById(R.id.edit03);
        et4=findViewById(R.id.edit04);
        i01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(Profile.this , Settings.class);
                startActivity(i1);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty()||et2.getText().toString().isEmpty()||et3.getText().toString().isEmpty()||et4.getText().toString().isEmpty()){
                    Toast.makeText(Profile.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.update01(userEmail,et3.getText().toString(),et4.getText().toString(),et1.getText().toString(),et2.getText().toString());
                    Intent i1 = new Intent(Profile.this, MainActivity.class);
                    startActivity(i1);
                    Toast.makeText(Profile.this, "Changes Saved", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        db=new DbHandler(this);
        userEmail = getSharedPreferences("user_prefs", MODE_PRIVATE)
                .getString("user_email", "Email not available");
    }
}