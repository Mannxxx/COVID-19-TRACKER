package com.example.trackercovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leo.simplearcloader.SimpleArcLoader;

public class Information extends AppCompatActivity {
    SimpleArcLoader simpleArcLoader;
    String html="<iframe width=\"100%\" height=\"100%\" src=\"https://maps.mapmyindia.com/corona?safety_alert\"></iframe>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        WebView webView = findViewById(R.id.webView);
        simpleArcLoader = findViewById(R.id.loader);
        simpleArcLoader.start();
        //Initialize
        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.info);

        //perform ItemSelectedListener

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.info:
                        return true;
                    case R.id.updates:
                        startActivity(new Intent(getApplicationContext(),
                                Updates.class));
                        overridePendingTransition(0,0);
                        return true;
//                    case R.id.search:
//                        startActivity(new Intent(getApplicationContext(),
//                                AffectedCountries.class));
//                        overridePendingTransition(0,0);
//                        return true;
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        webView.setWebViewClient(new information());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);

       // webView.loadUrl(html);
        webView.loadData(html, "text/html", null);
        simpleArcLoader.setVisibility(View.GONE);
        }


    private class information extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    }
