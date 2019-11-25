package com.example.final2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;

import java.lang.ref.WeakReference;
// for activity 3
// get phone's volume
public class VolumeChangeObserver {
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";

    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";

    public interface VolumeChangeListener {
        void VolumeChanged(int volume);
    }

    private VolumeChangeListener myVolumeChangeListener;
    private VolumeBroadcastReceiver myVolumeBroadcastReceiver;
    private Context context;
    private AudioManager audioManager;
    private boolean registeredFlag = false;

    public VolumeChangeObserver(Context context) {
        this.context = context;
        audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    public int getCurrentVolume() {
        try {
            return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public int getMaxVolume() {
        try {
            return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        } catch (NullPointerException e) {
            return 15;
        }
    }

    public VolumeChangeListener getVolumeChangeListener() {
        return myVolumeChangeListener;
    }

    public void setVolumeChangeListener(VolumeChangeListener setvolumeChangeListener) {
        myVolumeChangeListener = setvolumeChangeListener;
    }

    public void registerReceiver() {
        myVolumeBroadcastReceiver = new VolumeBroadcastReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(VOLUME_CHANGED_ACTION);
        context.registerReceiver(myVolumeBroadcastReceiver, filter);
        registeredFlag = true;
    }

    public void unregisterReciver() {
        if (registeredFlag) {
            try {
                context.unregisterReceiver(myVolumeBroadcastReceiver);
                myVolumeChangeListener = null;
                registeredFlag = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public class VolumeBroadcastReceiver extends BroadcastReceiver {
        private WeakReference<VolumeChangeObserver> myObserverWeakReference;

        public VolumeBroadcastReceiver(VolumeChangeObserver setvolumeChangeObserver) {
            myObserverWeakReference = new WeakReference<>(setvolumeChangeObserver);
        }

        public void onReceive(Context context, Intent intent) {
            if (VOLUME_CHANGED_ACTION.equals(intent.getAction())&&
                    (intent.getIntExtra(EXTRA_VOLUME_STREAM_TYPE, -1) == AudioManager.STREAM_MUSIC)) {
                VolumeChangeObserver observer = myObserverWeakReference.get();
                try {
                    VolumeChangeListener listener = observer.getVolumeChangeListener();
                    int volume = observer.getCurrentVolume();
                    if (volume >= 0) {
                        listener.VolumeChanged(volume);
                    }
                } catch (NullPointerException e) {
                    Log.e("Exception", e.toString());
                }

            }
        }
    }

}
