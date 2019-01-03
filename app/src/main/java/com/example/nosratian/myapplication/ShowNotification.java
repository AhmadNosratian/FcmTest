package com.example.nosratian.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ShowNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);


        Intent notificationData = getIntent();

        if (notificationData.getData() != null)
            Toast.makeText(this, "you done it", Toast.LENGTH_LONG).show();
    }
}
