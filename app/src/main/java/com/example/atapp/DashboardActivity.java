package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void toHome(View view) {
        Intent intent = new Intent(DashboardActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void toProfile(View view) {
    }

    public void toSos(View view) {
        Intent intent = new Intent(DashboardActivity.this, SosActivity.class);
        startActivity(intent);
    }
}