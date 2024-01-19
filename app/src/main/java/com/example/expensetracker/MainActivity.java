package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DbHandler db;
    Button btn1,btn2;
    Intent intent;
    EditText et01,et02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.loginbtn);
        btn2 = findViewById(R.id.newaccount);
        et01 = findViewById(R.id.text2);
        et02 = findViewById(R.id.txt3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et01.getText().toString().isEmpty() || et02.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "All Fields Are Required", Toast.LENGTH_LONG).show();
                } else {
                    // Inside the login button's OnClickListener
                    String EMAIL = et01.getText().toString();
                    String PASS = et02.getText().toString();

                    db = new DbHandler(MainActivity.this);

                    if (db.checkUserCredentials(EMAIL, PASS)) {
                        Toast.makeText(MainActivity.this, "Successfully Login...", Toast.LENGTH_SHORT).show();

                        getSharedPreferences("user_prefs", MODE_PRIVATE)
                                .edit()
                                .putString("user_email", EMAIL)
                                .apply();
                        Intent interfaceIntent = new Intent(MainActivity.this, mpin.class);
                        startActivity(interfaceIntent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid email or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
