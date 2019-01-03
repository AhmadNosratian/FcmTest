package com.example.nosratian.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /***
         when the app is in background
         fcm send data to main thread with intent
         in default launcher get intent and parse it
         ***/
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        Toast.makeText(this, title, Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
