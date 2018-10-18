package com.weather.pc.searchengine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.weather.pc.searchengine.R;

public class DetailActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webView=findViewById(R.id.webview);

        Bundle bundle=getIntent().getExtras();
        webView.loadUrl(bundle.getString("url"));
    }
}
