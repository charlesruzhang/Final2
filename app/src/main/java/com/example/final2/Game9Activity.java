package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Game9Activity extends AppCompatActivity {

    private SensorManager mySensorManager;
    private Sensor mySensor;
    private float myLux;
    private TextView text;
    private ProgressBar progressBar;
    private int i = 0;
    private TextView currentpercent;
    private ImageView batteryanimate;
    private AnimationDrawable battery1;
    private AnimationDrawable battery2;
    private AnimationDrawable battery3;
    private AnimationDrawable battery4;
    private AnimationDrawable battery5;


    /** hint button. **/
    private Button hintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game9);
        text = findViewById(R.id.myluxvavlue);
        currentpercent = findViewById(R.id.currentp);
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mySensorManager.registerListener(listener, mySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        progressBar = findViewById(R.id.progressbar);
        batteryanimate = findViewById(R.id.nocharge);



        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());
    }
    protected void onDestroy() {
        if (mySensorManager != null) {
            mySensorManager.unregisterListener(listener);
        }
        super.onDestroy();
    }
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(listener);
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.e("I", i+"");
            if (i >=0 && i <= 5) {
                batteryanimate.setBackgroundResource(R.drawable.activity9animation0);
                battery1 = (AnimationDrawable) batteryanimate.getBackground();
                battery1.start();
            } else if (i > 5 && i <=25) {
                battery1.stop();
                batteryanimate.setBackgroundResource(R.drawable.activity9animation25);
                battery2 = (AnimationDrawable) batteryanimate.getBackground();
                battery2.start();
            } else if (i > 25 && i <=50) {
                battery2.stop();
                batteryanimate.setBackgroundResource(R.drawable.activity9animation50);
                battery3 = (AnimationDrawable) batteryanimate.getBackground();
                battery3.start();
            } else if (i > 50 && i <=75) {
                battery3.stop();
                batteryanimate.setBackgroundResource(R.drawable.activity9animation75);
                battery4 = (AnimationDrawable) batteryanimate.getBackground();
                battery4.start();
            } else {
                battery4.stop();
                batteryanimate.setBackgroundResource(R.drawable.activity9animation100);
                battery5 = (AnimationDrawable) batteryanimate.getBackground();
                battery5.start();
            }

            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                myLux = sensorEvent.values[0];
                if (i >= 100) {
                    i = 100;
                    battery5.stop();
                    batteryanimate.setImageResource(R.drawable.batteryfull);
                    Pass dialog = new Pass();
                    dialog.levelPassed(9);
                    dialog.show(getSupportFragmentManager(), "Pass");
                    onPause();
                }
                text.setText("Value is: " + myLux);
                currentpercent.setText(i + "%");
                progressBar.setProgress(i);
                if (myLux > 600) {
                    i = i + 1;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /** enter your hint for this level in this method. **/
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("Using the sunlight");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
