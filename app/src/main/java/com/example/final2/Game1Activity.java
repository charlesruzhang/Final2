package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


/** this game is about Drinking water by sensor in Android.
 *  Picture should be replaced.
 *  set a unused button to setText eg. "Drink"
 *  three pictures: empty and half and full
 *  textView showing the progress of game
 *  Hint button Hint text
 *  almost finished --- 11/29
 */

public class Game1Activity extends AppCompatActivity {

    private static final String TAG = "TEST";

    private SensorManager mySensorManager;

    private Sensor myAccelerometer;

    private TestSensorListener mySensorListener;

    private final double accNumber = 9.3;

    /** hint button. **/
    private Button hintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        setTitle("GAME1");
        setInitialViews();

        ImageView full = findViewById(R.id.full);
        ImageView half = findViewById(R.id.half);
        ImageView empty = findViewById(R.id.empty);
        full.setVisibility(View.VISIBLE);
        half.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);

        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());

        //useless button
        Button drinkButton = findViewById(R.id.drinkbutton);
        Button pourButton = findViewById(R.id.pourbutton);
        drinkButton.setOnClickListener(unused -> {
            drinkButton.setText("It is too Hot");
        });
        pourButton.setOnClickListener(unused -> {
            pourButton.setText("You could not pour it");
        });
        mySensorListener = new TestSensorListener();
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        // 注册传感器监听函数
        mySensorManager.registerListener(mySensorListener, myAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 注销监听函数
        mySensorManager.unregisterListener(mySensorListener);
    }

    private void setInitialViews() {
        TextView sensorInfotex = findViewById(R.id.infotext);
        sensorInfotex.setText("Drinking Water Game");
    }

    class TestSensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            // (0, c1, c2) -> (x , y ,z) event.value[i]
            Log.i(TAG, "SensorChangedValue: " + event.values[0] + ", " + event.values[1] + ", " + event.values[2]);

            TextView sensorInfotext = findViewById(R.id.infotext);
            ImageView full = findViewById(R.id.full);
            ImageView half = findViewById(R.id.half);
            ImageView empty = findViewById(R.id.empty);

            if (sensorInfotext.getText().toString().equals("Finished") && event.values[1] > accNumber) {
                onPause();
                Pass dialog = new Pass();
                dialog.levelPassed(1);
                dialog.show(getSupportFragmentManager(), "Pass");
            }


            if (half.getVisibility() == View.VISIBLE && event.values[1] < (-1 * accNumber)
                    && sensorInfotext.getText().toString().equals("Drink it again")) {
                sensorInfotext.setText("Finished");
                half.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
            }

            if (half.getVisibility() == View.VISIBLE && event.values[1] > accNumber) {
                sensorInfotext.setText("Drink it again");
            } else if (event.values[1] < (-1 * accNumber) && full.getVisibility() == View.VISIBLE) {
                sensorInfotext.setText("Drink Water");
                full.setVisibility(View.GONE);
                half.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.i(TAG, "onAccuracyChanged");
        }
    }

    /** enter your hint for this level in this method. **/
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("Try to reverse the screen");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
