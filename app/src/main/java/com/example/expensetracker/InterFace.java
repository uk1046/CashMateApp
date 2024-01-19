package com.example.expensetracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class InterFace extends AppCompatActivity {
    ImageButton b1, b2, b3, b4, b5;
    TextView t1, t2,f;

    int creditTotal, debitTotal;
    ImageButton btn,btn1;
    ArrayList<HashMap<String, String>> userRecordList;
    String userEmail;
    ListAdapter adapter;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_face);

        b1 = findViewById(R.id.btn001);
        b2 = findViewById(R.id.btn002);
        b3 = findViewById(R.id.btn003);
        b4 = findViewById(R.id.btn004);
        btn = findViewById(R.id.add);
        btn1 = findViewById(R.id.download);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(InterFace.this, download1.class);
                startActivity(i1);
            }
        });

        t1 = findViewById(R.id.t03);
        t1.setTextColor(Color.GREEN);
        t2 = findViewById(R.id.t06);
        t2.setTextColor(Color.RED);

        userRecordList = new ArrayList<>();
        adapter = new ListAdapter(userRecordList);

        ListView listView = findViewById(R.id.user_list);
        listView.setAdapter(adapter);

        db=new DbHandler(this);

        f=findViewById(R.id.fn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(InterFace.this, AddData.class);
                startActivity(i1);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(InterFace.this, Investment.class);
                startActivity(i1);
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(InterFace.this, Insurance.class);
                startActivity(i1);
                finish();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(InterFace.this, Settings.class);
                startActivity(i1);
                finish();
            }
        });



        loadAndDisplayRecords();
    }


    private void loadAndDisplayRecords() {
        DbHandler dbHandler = new DbHandler(InterFace.this);

        userRecordList = dbHandler.GetUsers();

        // Calculate credit and debit totals
        calculateCreditDebitTotals(userRecordList);

        // Update the adapter with the new data
        adapter.refreshData(userRecordList);
        adapter.notifyDataSetChanged();
    }

    private void calculateCreditDebitTotals(ArrayList<HashMap<String, String>> records) {
        creditTotal = 0;
        debitTotal = 0;

        for (HashMap<String, String> record : records) {
            String type = record.get("type");
            int amount = Integer.parseInt(record.get("amount"));

            if ("Credit".equals(type)) {
                creditTotal += amount;
            } else if ("Debit".equals(type)) {
                debitTotal += amount;
            }
        }
        t1.setText("₹" + creditTotal);
        t2.setText("₹" + debitTotal);
    }

    public class ListAdapter extends BaseAdapter {
        private ArrayList<HashMap<String, String>> data;
        ListAdapter(ArrayList<HashMap<String, String>> data) {
            this.data = new ArrayList<>(data);
            reverseData();
        }
        private void reverseData() {
            Collections.reverse(data);
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_row, parent, false);

            TextView typeTextView = view.findViewById(R.id.typeTextView);
            TextView dateTextView = view.findViewById(R.id.dateTextView);
            TextView timeTextView = view.findViewById(R.id.timeTextView);
            TextView methodTextView = view.findViewById(R.id.methodTextView);
            TextView amountTextView = view.findViewById(R.id.amountTextView);
            TextView notesTextView = view.findViewById(R.id.notes);

            HashMap<String, String> record = data.get(position);

            typeTextView.setText(record.get("type"));
            dateTextView.setText(record.get("date"));
            timeTextView.setText(record.get("time"));
            methodTextView.setText(record.get("method"));
            amountTextView.setText("₹" + record.get("amount"));
            notesTextView.setText(record.get("note"));

            if ("Debit".equals(record.get("type"))) {
                amountTextView.setTextColor(Color.RED);
            } else {
                amountTextView.setTextColor(Color.GREEN);
            }
            return view;
        }

        public void refreshData(ArrayList<HashMap<String, String>> newData) {
            data.clear();
            data.addAll(newData);
            reverseData();
            notifyDataSetChanged();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        userEmail = getSharedPreferences("user_prefs", MODE_PRIVATE)
                .getString("user_email", "Email not available");

        f.setText(db.getFirstName(userEmail));

        loadAndDisplayRecords();
    }
}
