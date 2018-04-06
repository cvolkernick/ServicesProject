package com.example.cvolk.servicesproject.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.example.cvolk.servicesproject.Constants;
import com.example.cvolk.servicesproject.MainActivity;
import com.example.cvolk.servicesproject.R;
import org.greenrobot.eventbus.EventBus;

public class MusicService extends Service {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION))
        {
            startForeground();
        }
        else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION))
        {

        }
        else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION))
        {
            EventBus.getDefault().post("play");
        }
        else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION))
        {
            EventBus.getDefault().post("pause");
        }
        else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION))
        {
            stopForeground(true);
            stopSelf();
        }

        return START_STICKY;
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, MusicService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, MusicService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, MusicService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Truiton Music Player")
                .setTicker("Truiton Music Player")
                .setContentText("My Music")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous,
                        "Previous", ppreviousIntent)
                .addAction(android.R.drawable.ic_media_play, "Play",
                        pplayIntent)
                .addAction(android.R.drawable.ic_media_pause, "Pause",
                        pnextIntent).build();
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);
    }
}