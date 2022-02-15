package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LogoutActivity extends AppCompatActivity {

    Button butLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        butLogout = (Button) findViewById(R.id.butLogout);

        butLogout.setOnClickListener(v -> {
            Intent intent = new Intent(LogoutActivity.this, LogInActivity.class);
            startActivity(intent);
        });
    }
}