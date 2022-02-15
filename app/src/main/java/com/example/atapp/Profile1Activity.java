package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile1Activity extends AppCompatActivity {

    EditText editName;
    EditText editAddr;
    EditText editPass;
    EditText editConf;

    Button butSave;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        editName = (EditText) findViewById(R.id.textName);
        editAddr = (EditText) findViewById(R.id.textAddress);
        editPass = (EditText) findViewById(R.id.textPass);
        editConf = (EditText) findViewById(R.id.textConf);

        butSave = (Button) findViewById(R.id.butSave);

        butSave.setOnClickListener(v -> {
            String getName = editName.getText().toString();
            String getAddr = editAddr.getText().toString();
            String getPass = editPass.getText().toString();
            String getConf = editConf.getText().toString();


            if (getPass.equals(getConf)){
                myRef.child("user").child(getName).child("address").setValue(getAddr);
            }
        });


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