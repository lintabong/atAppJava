package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    ImageView imgDips;
    TextView textActivity;
    TextView textStatus;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgDips         = (ImageView) findViewById(R.id.humanReceive);
        textActivity    = (TextView) findViewById(R.id.textActivity);
        textStatus      = (TextView) findViewById(R.id.textStatus);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String myImage = dataSnapshot.child("image").getValue(String.class);
                String myActivity = dataSnapshot.child("activity").getValue(String.class);
                String myStatus = dataSnapshot.child("status").getValue(String.class);

                Picasso.get().load(myImage).into(imgDips);
                textActivity.setText(myActivity);
                textStatus.setText(myStatus);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}