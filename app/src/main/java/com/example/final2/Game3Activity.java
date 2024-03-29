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
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        Button playButton = findViewById(R.id.playbutton);
        setTitle("GAME3");
        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());

        MediaPlayer mp = MediaPlayer.create(this, R.raw.game3music);


        playButton.setOnClickListener(unused -> {
            mp.start();
            playFlag = true;
        });

        stop = findViewById(R.id.stop);
        stop.setText("Stop");

        stop.setOnClickListener(unused -> {
            mp.stop();
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
    private void releaseMediaPlayer() {
        try {
            if (mp != null) {
                if (mp.isPlaying())
                    mp.stop();
                mp.release();
                mp = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (loudandlow.getText().toString().equals("Too Low")) {
            onPause();
            Pass dialog = new Pass();
            dialog.levelPassed(3);
            dialog.show(getSupportFragmentManager(), "Pass");
            mp.stop();
        }
    }

    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("Maybe turn on the music and use the volume button?");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
