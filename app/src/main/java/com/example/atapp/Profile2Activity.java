package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
    }

    public void profileSetting(View view) {
        Intent intent = new Intent(Profile2Activity.this, Profile1Activity.class);
        startActivity(intent);
    }

    public void profileToSOS(View view) {
        Intent intent = new Intent(Profile2Activity.this, SosActivity.class);
        startActivity(intent);
    }

    public void ProfileToHome(View view) {
        Intent intent = new Intent(Profile2Activity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void profileToLogout(View view) {
        Intent intent = new Intent(Profile2Activity.this, LogoutActivity.class);
        startActivity(intent);
    }

    public void profiletoHome(View view) {
        Intent intent = new Intent(Profile2Activity.this, HomeActivity.class);
        startActivity(intent);
    }
}