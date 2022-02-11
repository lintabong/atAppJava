package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

    }

    public void callPolice(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","119", null));
        startActivity(intent);
    }

    public void callFF(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","112", null));
        startActivity(intent);
    }

    public void callAmbulance(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","110", null));
        startActivity(intent);
    }

    public void sosToHome(View view) {
        Intent intent = new Intent(SosActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}