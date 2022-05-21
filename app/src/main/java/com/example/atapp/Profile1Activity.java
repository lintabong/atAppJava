package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile1Activity extends AppCompatActivity {

    EditText editName;
    EditText editAddr;
    EditText editPass;
    EditText editConf;

    Button butSave;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mychnl", "mychannel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mychnl")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Antithief Notification")
                .setContentText("Bahaya");

        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String myNotif = dataSnapshot.child("pushnotif").getValue(String.class);

                if (myNotif.equals("active")) {
                    notificationManagerCompat.notify(1, notification);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
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