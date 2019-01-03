package com.example.nosratian.myapplication.Service;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.nosratian.myapplication.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseInstanceService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "onMessageReceived:==> message received");

        if (remoteMessage.getData().isEmpty())
            showNotificatoin(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        else
            showNotificatoin(remoteMessage.getData());
    }

    private void showNotificatoin(Map<String, String> data) {
        String title = data.get("title");
        String body = data.get("body");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "com.example.nosratian.test";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID
                    , "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setDescription("EDMT Channel");
            manager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_notification)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setWhen(System.currentTimeMillis())
                .setContentInfo("Info")
                .setContentTitle(title)
                .setContentText(body);

        manager.notify(new Random().nextInt(), builder.build());





    }

    private void showNotificatoin(String title, String body) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "com.example.nosratian.test";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID
                    , "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setDescription("EDMT Channel");
            manager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_notification)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setWhen(System.currentTimeMillis())
                .setContentInfo("Info")
                .setContentTitle(title)
                .setContentText(body);

        manager.notify(new Random().nextInt(), builder.build());

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG, "FIREBASE_TOKEN: " + s);
    }
}
