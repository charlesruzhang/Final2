package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * game3: using volume button to pass the game.
 * initially set to 0
 * start the music
 * some hints to increase the volume and then decrease it.
 */
public class Game3Activity extends AppCompatActivity implements VolumeChangeObserver.VolumeChangeListener {
    private MediaPlayer mp = new MediaPlayer();

    private final static String TAG = "TEST";

    private VolumeChangeObserver myObserver;

    private boolean playFlag = false;

    /** hint button. **/
    private Button hintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        Button playButton = findViewById(R.id.playbutton);
        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());

        MediaPlayer mp = MediaPlayer.create(this, R.raw.quit);


        playButton.setOnClickListener(unused -> {
            mp.start();
            playFlag = true;
        });


        myObserver = new VolumeChangeObserver(this);
        myObserver.setVolumeChangeListener(this);
        int initVolume = myObserver.getCurrentVolume();
        Log.e(TAG, "initVolume = " + initVolume);
    }

    /**
     * with media player.
     */
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
        }
    }

    protected void onPause() {
        myObserver.unregisterReciver();
        super.onPause();
    }

    protected void onResume() {
        myObserver.registerReceiver();
        super.onResume();
    }

    public void VolumeChanged(int volume) {
        TextView loudandlow = findViewById(R.id.loudandlow);
        Log.e(TAG, "onVolumeChanged()--->volume = " + volume);
        Log.e(TAG, playFlag+"");
        if (playFlag) {
            if (volume > 13) {
                loudandlow.setText("TOO LOUD");
            } else if (volume < 3 && loudandlow.getText().toString().equals("TOO LOUD")) {
                loudandlow.setText("Too Low");
            }
        }
    }

    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("enter hint here. (e.g. Game6Activity");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
