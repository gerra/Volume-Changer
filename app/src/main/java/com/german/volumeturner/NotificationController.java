package com.german.volumeturner;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;

public class NotificationController {
    private static final int NOTIFICATION_ID = 658487;

    @NonNull
    private final NotificationCreator mNotificationCreator;

    public NotificationController(@NonNull NotificationCreator notificationCreator) {
        mNotificationCreator = notificationCreator;
    }

    void showNotification(@NonNull Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mNotificationCreator.createNotification(context);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
