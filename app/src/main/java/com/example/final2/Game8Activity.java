package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game8Activity extends AppCompatActivity {

    /** hint button. **/
    private Button hintButton;
    private ImageButton jeff;
    private TextView times;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game8);

        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());

        times = findViewById(R.id.times);
        jeff = findViewById(R.id.jeff);
        jeff.setOnClickListener(v -> {
            num = num + 10;
            times.setText("" + num);
            test();
        });
        jeff.setOnLongClickListener(unused -> {
            num = num + 5;
            times.setText("" + num);
            test();
            return true;
        });

    }

    private void test() {
        if (num == 125) {
            Pass dialog = new Pass();
            dialog.levelPassed(8);
            dialog.show(getSupportFragmentManager(), "Pass");
        }
    }

    /** enter your hint for this level in this method. **/
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("LONG CLICK!");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
