package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** button clicking.
 * click 6 buttons in 10 second will pass the game.
 */

public class Game6Activity extends AppCompatActivity {
    private int click = 0;
    private TextView timer;
    private Button start;
    private Button clickButton;
    private TextView win;
    private CountDownTimer countDownTimer;
    private long timeLeft = 10000;
    private boolean timerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        timer = findViewById(R.id.timer);
        start = findViewById(R.id.start);
        start.setOnClickListener(v -> startStop());
        clickButton = findViewById(R.id.clickButton);
        clickButton.setOnClickListener(v -> clickCounting());
        win = findViewById(R.id.winSignal);
    }

    private void startStop() {
        if (timerRunning) {
            stop();
        } else {
            start();
        }
    }

    private void start() {
        countDownTimer =  new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        start.setText("stop");
        timerRunning = true;
    }

    private void stop() {
        countDownTimer.cancel();
        start.setText("start");
        timerRunning = false;
    }
    private void updateTimer() {
        int sec = (int) (timeLeft / 1000);
        String timeLeftText = "" + sec;
        timer.setText(timeLeftText);
        if (sec == 0) {
            timeLeft = 10000;
            start.setText("start");
            timerRunning = false;
            if (click == 6) {
                click = 0;
                win.setText("you pass");
                win.setVisibility(View.VISIBLE);
            } else {
                click = 0;
                win.setText("try again");
                win.setVisibility(View.VISIBLE);
            }
        }
    }

    private void clickCounting() {
        if (timerRunning) {
            click++;
        }
        int wide = findViewById(R.id.relativeLayout).getWidth() - clickButton.getWidth();
        int height = findViewById(R.id.relativeLayout).getHeight() - clickButton.getHeight();
        int setX = (int) (Math.random() * wide);
        int setY = (int) (Math.random() * height);
        clickButton.setX(setX);
        clickButton.setY(setY);
    }
}
