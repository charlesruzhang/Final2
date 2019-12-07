package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
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
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                myLux = sensorEvent.values[0];
                if (i >= 100) {
                    i = 100;
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
        dialog.addHint("enter hint here. (e.g. Game6Activity");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
