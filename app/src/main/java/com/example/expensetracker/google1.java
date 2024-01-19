package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class google1 extends AppCompatActivity {
    private WebView webView;
    ImageButton ig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google1);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.angelone.in/mutual-funds/mf-schemes/icici-prudential-technology-fund-direct-plan-growth");

        ig = (ImageButton) findViewById(R.id.back01);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(google1.this , Investment.class);
                startActivity(i1);
                finish();
            }
        });
    }
}