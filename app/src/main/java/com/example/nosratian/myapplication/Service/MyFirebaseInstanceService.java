package com.example.nosratian.myapplication.Service;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.nosratian.myapplication.MainActivity;
import com.example.nosratian.myapplication.R;
import com.example.nosratian.myapplication.ShowNotification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseInstanceService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseService";
    public static final String EXTRA_NOTIF_TITLE = "title";
    public static final String EXTRA_NOTIF_BODY = "body";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "onMessageReceived:==> message received");



        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            showNotificatoin(remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showNotificatoin(remoteMessage.getNotification().getTitle()
                    , remoteMessage.getNotification().getBody());
        }


    }



    private void showNotificatoin(Map<String, String> data) {
        String title = data.get("title");
        String body = data.get("body");
        String click_action_2  = data.get("click_action");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        Intent intent = new Intent(click_action_2);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_NOTIF_TITLE, title);
        intent.putExtra(EXTRA_NOTIF_BODY, body);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, new Random().nextInt(),
                intent, PendingIntent.FLAG_ONE_SHOT);

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
                .setContentIntent(pendingIntent)
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
