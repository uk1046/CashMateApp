package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class start100 extends AppCompatActivity {
    private WebView webView;
    ImageButton ig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start100);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.angelone.in/ppc/mutual-funds-v2?utm_source=google&utm_medium=cpc&utm_campaign=B2C_Search_Brand_Mutual_Fund&network=g&keyword=angel%20broking%20sip&matchtype=e&creative=648060427417&device=c&devicemodel=&gad_source=1&gclid=Cj0KCQiAtaOtBhCwARIsAN_x-3LyNmtrLZPqRWgHMsMfHYKxfHtd9SbrzJAHLvXUVskiCSh8kFOhenEaApQmEALw_wcB");

        ig = (ImageButton) findViewById(R.id.back01);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(start100.this , Investment.class);
                startActivity(i1);
                finish();
            }
        });
    }
}