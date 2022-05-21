package com.example.atapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SosActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

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