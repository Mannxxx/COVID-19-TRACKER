package com.example.trackercovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread th=new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(Exception t){}
                finally {
                    Intent obj1=new Intent(splashScreen.this,MainActivity.class);
                    startActivity(obj1);
                    finish();
                }
            }
        } ;
        th.start();
    }
}
