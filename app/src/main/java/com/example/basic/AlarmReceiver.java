package com.example.basic;
import java.util.Random;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    public static String randomStr;

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] array = context.getResources().getStringArray(R.array.NotificationText);
        randomStr = array[new Random().nextInt(array.length)];

        Intent i = new Intent(context,notiscreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Yetti")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Yetti Reminder")
                .setContentText(randomStr)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());






    }

}
