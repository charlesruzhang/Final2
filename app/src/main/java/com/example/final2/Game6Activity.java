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
    private Button hintButton;
    private CountDownTimer countDownTimer;
    private long timeLeft = 10000;
    private boolean timerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);
        setTitle("GAME6");
        timer = findViewById(R.id.timer);
        start = findViewById(R.id.start);
        start.setOnClickListener(v -> startStop());
        clickButton = findViewById(R.id.clickButton);
        clickButton.setOnClickListener(v -> clickCounting());
        clickButton.setVisibility(View.INVISIBLE);
        win = findViewById(R.id.winSignal);
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());
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
                clickButton.setVisibility(View.VISIBLE);
                updateTimer();
            }

            @Override
            public void onFinish() {
                timeLeft = 10000;
                timer.setText("10");
                start.setText("start");
                timerRunning = false;
                if (click == 6) {
                    click = 0;
                    win.setText("you pass");
                    win.setVisibility(View.VISIBLE);
                    Pass dialog = new Pass();
                    dialog.levelPassed(6);
                    dialog.show(getSupportFragmentManager(), "Pass");
                } else {
                    click = 0;
                    win.setText("try again");
                    win.setVisibility(View.VISIBLE);
                }
                start.setText("start");
                timerRunning = false;
                if (click == 6) {
                    click = 0;
                    clickButton.setVisibility(View.INVISIBLE);
                    win.setText("you pass");
                    win.setVisibility(View.VISIBLE);
                    Pass dialog = new Pass();
                    dialog.levelPassed(6);
                    dialog.show(getSupportFragmentManager(), "Pass");
                } else {
                    click = 0;
                    clickButton.setVisibility(View.INVISIBLE);
                    win.setText("try again");
                    win.setVisibility(View.VISIBLE);
                }
            }
        }.start();
        start.setText("stop");
        timerRunning = true;
    }

    private void stop() {
        countDownTimer.cancel();
        clickButton.setVisibility(View.INVISIBLE);
        start.setText("start");
        timerRunning = false;
    }
    private void updateTimer() {
        int sec = (int) (timeLeft / 1000);
        String timeLeftText = "" + sec;
        timer.setText(timeLeftText);
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

    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("press click button 6 times in 10 seconds");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}