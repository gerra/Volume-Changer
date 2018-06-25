package com.german.volumeturner;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class VolumeChangeHelper {
    private VolumeChangeHelper() {
        // no instance
    }

    private static final String EXTRA_VOLUME_DIRECTION = "VOLUME_DIRECTION";

    public static final int VOLUME_UP = 1;
    public static final int VOLUME_DOWN = 2;

    private static final int VOLUME_UP_REQUEST_CODE = 1;
    private static final int VOLUME_DOWN_REQUEST_CODE = 2;

    @NonNull
    public static PendingIntent createVolumeChangeAction(@NonNull Context context, @VolumeDirection int volumeDiretion) {
        Intent intent = new Intent(context, VolumeChangeReceiver.class)
                .setAction(VolumeChangeReceiver.ACTION_VOLUME_CHANGE)
                .putExtra(EXTRA_VOLUME_DIRECTION, volumeDiretion);
        int requestCode = volumeDiretion == VOLUME_UP
                ? VOLUME_UP_REQUEST_CODE
                : VOLUME_DOWN_REQUEST_CODE;
        return PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @VolumeDirection
    public static int getDirectionFromIntent(@NonNull Intent intent) {
        return intent.getIntExtra(EXTRA_VOLUME_DIRECTION, VOLUME_DOWN);
    }

    public static void changeVolume(@NonNull Context context, @VolumeDirection int volumeDirection) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int direction = volumeDirection == VOLUME_DOWN
                ? AudioManager.ADJUST_LOWER
                : AudioManager.ADJUST_RAISE;
        audioManager.adjustVolume(direction, AudioManager.FLAG_PLAY_SOUND);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VOLUME_UP, VOLUME_DOWN})
    public @interface VolumeDirection {
    }
}
