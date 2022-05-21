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
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    ImageView imgDips;
    TextView textActivity;
    TextView textStatus;
    TextView textTime;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgDips = (ImageView) findViewById(R.id.humanReceive);
        textActivity = (TextView) findViewById(R.id.textActivity);
        textStatus = (TextView) findViewById(R.id.textStatus);
        textTime = (TextView) findViewById(R.id.textTime);

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
                String myImage = dataSnapshot.child("image").getValue(String.class);
                String myActivity = dataSnapshot.child("activity").getValue(String.class);
                String myStatus = dataSnapshot.child("status").getValue(String.class);
                String myNotif = dataSnapshot.child("pushnotif").getValue(String.class);

                Picasso.get().load(myImage).into(imgDips);
                textActivity.setText(myActivity);
                textStatus.setText(myStatus);

                if (myNotif.equals("active")) {
                    notificationManagerCompat.notify(1, notification);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:MM");
        textTime.setText(formatter.format(today));
        blink();
    }

    public void homeToProfile(View view) {
        Intent intent = new Intent(HomeActivity.this, Profile1Activity.class);
        startActivity(intent);
    }

    public void homeToSos(View view) {
        Intent intent = new Intent(HomeActivity.this, SosActivity.class);
        startActivity(intent);
    }

    public void homeToLogout(View view) {
        Intent intent = new Intent(HomeActivity.this, LogoutActivity.class);
        startActivity(intent);
    }

    private void blink() {
        final Handler hander = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        if (textTime.getVisibility() == View.VISIBLE) {
                            textTime.setVisibility(View.INVISIBLE);
                        } else {
                            textTime.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
}