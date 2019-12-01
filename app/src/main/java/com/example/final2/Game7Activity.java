package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/** music playing game.
 * seven tones
 * a song with seven button represent do re mi...
 * GLHF
 * vibrator
 */

public class Game7Activity extends AppCompatActivity {

    private Vibrator myVibrator;

    private MediaPlayer mp = new MediaPlayer();

    private Linklist myLinklist = new Linklist();

    /** hint button. **/
    private Button hintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game7);
        myVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

        TextView finishtext = findViewById(R.id.testtext);

        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());

        //restart the music
        Button resetmusic = findViewById(R.id.resetMusic);
        resetmusic.setOnClickListener(unused -> {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.targetmusic);
            mp.start();
        });

        //reset the linkedlist
        Button reset = findViewById(R.id.resetButtons);
        reset.setOnClickListener(unused -> {
            myLinklist = new Linklist();
        });

        Button finish = findViewById(R.id.finish);

        finish.setOnClickListener(unused -> {
            if (checked()) {
                finishtext.setText("tada you pass");
                Log.e("TEST", myLinklist.currentSize + "");
            } else {
                finishtext.setText("Incorrect!!! U know nothing about piano!!!");
                Log.e("TEST", myLinklist.currentSize + "");
                myLinklist = new Linklist();
            }
        });

        // register buttons
        Button d = findViewById(R.id.d);
        Button r = findViewById(R.id.r);
        Button m = findViewById(R.id.m);
        Button f = findViewById(R.id.f);
        Button s = findViewById(R.id.s);
        Button l = findViewById(R.id.l);
        Button x = findViewById(R.id.x);

        d.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.d);
            mp.start();
            if (myLinklist.currentSize == 0) {
                myLinklist.add(0, true);
            } else if ((myLinklist.currentSize == 13 || myLinklist.currentSize == 1) &&
                    myLinklist.start.value) {
                myLinklist.add(0, true);
            } else {
                myLinklist.add(0, false);
            }
        });

        r.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.r);
            mp.start();
            if (myLinklist.currentSize == 11 || myLinklist.currentSize == 12
                    && myLinklist.start.value) {
                myLinklist.add(0, true);
            } else {
                myLinklist.add(0, false);
            }
        });

        m.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.m);
            mp.start();
            if (myLinklist.currentSize == 9 || myLinklist.currentSize == 10
                    && myLinklist.start.value) {
                myLinklist.add(0, true);
            } else {
                myLinklist.add(0, false);
            }

        });

        f.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.f);
            mp.start();
            if (myLinklist.currentSize == 7 || myLinklist.currentSize == 8
                    && myLinklist.start.value) {
                myLinklist.add(0, true);
            } else {
                myLinklist.add(0, false);
            }
        });

        s.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.s);
            mp.start();

            if (myLinklist.currentSize == 2 || myLinklist.currentSize == 3 || myLinklist.currentSize == 6
                    && myLinklist.start.value) {
                myLinklist.add(0, true);
            } else {
                myLinklist.add(0, false);
            }

        });

        l.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.l);
            mp.start();
            if (myLinklist.currentSize == 4 || myLinklist.currentSize == 5
                    && myLinklist.start.value) {
                myLinklist.add(0, true);
            } else {
                myLinklist.add(0, false);
            }

        });

        x.setOnClickListener(unused -> {
            vibe();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.x);
            mp.start();
            myLinklist.add(0,false);
        });
    }

    //to vibrate
    private void vibe() {
        myVibrator.vibrate(300);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
        }
    }

    //check if it is finished
    private boolean checked() {
        if (myLinklist.currentSize != 14) {
            return false;
        }
        for (int i = 0; i < myLinklist.currentSize; i++) {
            if (!myLinklist.getItem(i).value) {
                return false;
            }
        }
        return true;
    }

    /** enter your hint for this level in this method. **/
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("Listening to the BGM");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
