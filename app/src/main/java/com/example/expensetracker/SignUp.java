package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    DbHandler db;
    Button btn1, btn2;
    EditText et1, et2, et3, et4, fn, ln,pin1,pin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn1 = findViewById(R.id.signbtn);
        btn2 = findViewById(R.id.login01);
        et1 = findViewById(R.id.text3);
        et2 = findViewById(R.id.text2);
        et3 = findViewById(R.id.txt3);
        et4 = findViewById(R.id.txt4);
        fn = findViewById(R.id.fname);
        ln = findViewById(R.id.lname);

        pin1=findViewById(R.id.txt5);
        pin2=findViewById(R.id.txt6);
        db = new DbHandler(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("SignUp", "Sign up button clicked");

                    String FRST_NAME = fn.getText().toString();
                    String LAST_NAME = ln.getText().toString();
                    String MOB_NO = et1.getText().toString();
                    String EMAIL = et2.getText().toString();
                    String PASS = et4.getText().toString();
                    String CONFIRM_PASS = et3.getText().toString();

                    String MPIN = pin1.getText().toString();
                    String CMPIN = pin2.getText().toString();

                    Log.d("SignUp", "FRST_NAME: " + FRST_NAME);
                    Log.d("SignUp", "LAST_NAME: " + LAST_NAME);
                    Log.d("SignUp", "MOB_NO: " + MOB_NO);
                    Log.d("SignUp", "EMAIL: " + EMAIL);
                    Log.d("SignUp", "PASS: " + PASS);
                    Log.d("SignUp", "CONFIRM_PASS: " + CONFIRM_PASS);

                    if (FRST_NAME.isEmpty() || LAST_NAME.isEmpty() || MOB_NO.isEmpty() ||
                            EMAIL.isEmpty() || PASS.isEmpty() || CONFIRM_PASS.isEmpty()|| MPIN.isEmpty() ||CMPIN.isEmpty()) {
                        Toast.makeText(SignUp.this, "All Fields Are Required", Toast.LENGTH_LONG).show();
                    } else {
                        if (db.isUserExists(EMAIL)) {
                            Toast.makeText(SignUp.this, "Email already exists. Choose a different email.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (MOB_NO.length() == 10) {
                                if (PASS.length() >= 8) {
                                    if (PASS.matches(".*[@_.*].*")) {
                                        if (PASS.equals(CONFIRM_PASS) || pin1.equals(pin2)) {
                                            db.insertUserDetails(FRST_NAME, LAST_NAME, MOB_NO, EMAIL, PASS , MPIN);
                                            db.createRecordsTable();
                                            if (db.isRecordsTableExists()==true) {
                                                Toast.makeText(SignUp.this, "Sign-up Successful", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(SignUp.this, "Sign-up Unsuccessful", Toast.LENGTH_SHORT).show();
                                            }
                                            Intent login = new Intent(SignUp.this, MainActivity.class);
                                            startActivity(login);
                                            finish();
                                        } else {
                                            Toast.makeText(SignUp.this, "Passwords or Pin does'nt match", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(SignUp.this, "Password should contain special characters (@, _, or .)", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignUp.this, "Password should be minimum 8 characters", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignUp.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }  catch (Exception e) {
                    Log.e("SignUp", "Exception in onClick", e);
                    e.printStackTrace();
                    Toast.makeText(SignUp.this, "An error occurred. Check Logcat for details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SignUp.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        });
    }
}