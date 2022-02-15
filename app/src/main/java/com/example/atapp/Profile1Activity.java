package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
    }

    public void profileToSOS(View view) {
        Intent intent = new Intent(Profile1Activity.this, SosActivity.class);
        startActivity(intent);
    }

    public void profileToLogout(View view) {
        Intent intent = new Intent(Profile1Activity.this, LogoutActivity.class);
        startActivity(intent);
    }

    public void ProfileToHome(View view) {
        Intent intent = new Intent(Profile1Activity.this, HomeActivity.class);
        startActivity(intent);
    }
}