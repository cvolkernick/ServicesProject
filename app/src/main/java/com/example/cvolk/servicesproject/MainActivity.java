package com.example.cvolk.servicesproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cvolk.servicesproject.services.MusicService;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent();
    }

    public void handlingServices(View view) {
        ComponentName musicComponent = new ComponentName(getApplicationContext(), MusicService.class);

        switch (view.getId()) {
            case R.id.btnStartMusic:
                intent.setComponent(musicComponent);
                startService(intent);
                break;
        }
    }
}
