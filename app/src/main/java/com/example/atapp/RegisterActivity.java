package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText editName;
    EditText editPass;
    EditText editConf;
    Button butRegist;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = (EditText) findViewById(R.id.textUsername);
        editPass = (EditText) findViewById(R.id.textPassword);
        editConf = (EditText) findViewById(R.id.textConfirmPassword);
        butRegist = (Button) findViewById(R.id.butRegister);

        butRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = editName.getText().toString();
                String getPassword = editPass.getText().toString();
                String getConfirm  = editConf.getText().toString();
                
                if (getPassword.equals(getConfirm)){
                    myRef.child(getUsername).setValue(getPassword);
                } else{
                    Toast.makeText(RegisterActivity.this, "Password dan Confirm Password, tidak tepat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}