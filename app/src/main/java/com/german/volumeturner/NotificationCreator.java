package com.german.volumeturner;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

public class NotificationCreator {
    @NonNull
    public Notification createNotification(@NonNull Context context) {
        RemoteViews notificationView = new RemoteViews(context.getPackageName(), R.layout.volume_turner_notification);
        PendingIntent downAction = VolumeChangeHelper.createVolumeChangeAction(context, VolumeChangeHelper.VOLUME_DOWN);
        PendingIntent upAction = VolumeChangeHelper.createVolumeChangeAction(context, VolumeChangeHelper.VOLUME_UP);
        notificationView.setOnClickPendingIntent(R.id.volume_up, upAction);
        notificationView.setOnClickPendingIntent(R.id.volume_down, downAction);

        return new NotificationCompat.Builder(context)
                .setContent(notificationView)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(null)
                .build();
    }
}
