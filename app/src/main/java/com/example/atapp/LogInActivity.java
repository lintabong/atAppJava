package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editPassword;
    Button butLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editUsername = (EditText) findViewById(R.id.textUsername);
        editPassword = (EditText) findViewById(R.id.textPassword);
        butLogin = (Button) findViewById(R.id.butLogin);

        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = editUsername.getText().toString();
                String getPassword = editPassword.getText().toString();

                if (getUsername == "admin" && getPassword == "admin"){
                    Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogInActivity.this, "Maaf " + getUsername + getPassword, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}