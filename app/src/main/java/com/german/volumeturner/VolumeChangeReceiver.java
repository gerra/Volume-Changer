package com.german.volumeturner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VolumeChangeReceiver extends BroadcastReceiver {
    static final String ACTION_VOLUME_CHANGE = "ACTION_VOLUME_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !ACTION_VOLUME_CHANGE.equals(intent.getAction())) {
            return;
        }

        @VolumeChangeHelper.VolumeDirection
        int direction = VolumeChangeHelper.getDirectionFromIntent(intent);
        VolumeChangeHelper.changeVolume(context, direction);
    }
}
