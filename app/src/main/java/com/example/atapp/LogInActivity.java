package com.example.atapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editPassword;
    Button butLogin;

    TextView signin;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editUsername = (EditText) findViewById(R.id.textUsername);
        editPassword = (EditText) findViewById(R.id.textPassword);
        butLogin = (Button) findViewById(R.id.butLogin);

        signin = (TextView) findViewById(R.id.PTpleaseSignIn);
        myRef.child("user").child("lintang").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String myText = String.valueOf(task.getResult().getValue());
                //signin.setText(myText);
            }
        });





        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = editUsername.getText().toString();
                String getPassword = editPassword.getText().toString();

                myRef.child("user").child(getUsername).get().addOnCompleteListener(task -> {
                    String myText = String.valueOf(task.getResult().getValue());

                    if (getPassword.equals(myText)){
                        Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LogInActivity.this, "Maaf, username atau password anda salah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void loginToResgister(View view) {
        Intent intent = new Intent(LogInActivity.this,RegisterActivity.class);
        startActivity(intent);

    }
}