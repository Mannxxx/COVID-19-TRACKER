package com.example.trackercovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class Updates extends AppCompatActivity {

    RequestQueue requestQueue;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> post_title = new ArrayList<String>();
    ArrayList<String> post_author = new ArrayList<String>();
    ArrayList<String> post_description = new ArrayList<String>();
    ArrayList<String> post_image = new ArrayList<String>();
    int total;
    SimpleArcLoader simpleArcLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        requestQueue = Volley.newRequestQueue(this);

        simpleArcLoader = findViewById(R.id.loader);
        simpleArcLoader.start();
        //Initialize
        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.updates);

        //perform ItemSelectedListener

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.updates:
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                Information.class));
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

        fetchdata();
    }

    private void fetchdata() {
        String url="http://newsapi.org/v2/everything?top-headlines?country=in&q=corona&apiKey=6d0da98715da49f9a6399d3ac7656259";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    total= Integer.parseInt(response.getString("totalResults"));
                    for (int i = 0; i < 20; i++) {

                        post_title.add(response.getJSONArray("articles").getJSONObject(i).getString("title"));

                    }


                    for (int i = 0; i < 20; i++) {

                        post_author.add(response.getJSONArray("articles").getJSONObject(i).getString("author"));

                    }


                    for (int i = 0; i < 20; i++) {
                        post_description.add(response.getJSONArray("articles").getJSONObject(i).getString("description"));
                    }

                    for (int i = 0; i < 20; i++) {

                        post_image.add(response.getJSONArray("articles").getJSONObject(i).getString("urlToImage"));

                    }

                    initRecyclerView();
                }
                catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Updates.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    private void initRecyclerView() {

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new NewsAdapter(this,post_title,post_image,post_description,post_author);
        recyclerView.setAdapter(adapter);
        simpleArcLoader.setVisibility(View.GONE);


    }
}