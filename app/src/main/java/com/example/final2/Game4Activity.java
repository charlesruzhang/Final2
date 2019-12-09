package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;



import java.util.Random;

public class Game4Activity extends AppCompatActivity {

    /** hint button. **/
    private Button hintButton;
    private final int line = 6;
    private int presentNumber = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        setTitle("GAME4");
        findViewById(R.id.presentNumber).setVisibility(View.INVISIBLE);
        presentNumber = 0;
        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());
        Button startButton = findViewById(R.id.startButton4);
        startButton.setText("start");
        startButton.setOnClickListener(unused -> start());
    }

    /**
     *
     */
    private void start() {
        findViewById(R.id.startButton4).setVisibility(View.INVISIBLE);
        TextView present = findViewById(R.id.presentNumber);
        present.setVisibility(View.VISIBLE);
        present.setText(presentNumber + "");
        LinearLayout table = findViewById(R.id.Table);
        table.setOrientation(LinearLayout.HORIZONTAL);
        int[][] generator = generate(line);
        for (int i = 0; i < line; i++) {
            Log.e("new", "this is " + i);
            LinearLayout tableChunk = new LinearLayout(this);
            tableChunk.setOrientation(LinearLayout.VERTICAL);
            for (int j = 0; j < line; j++) {
                RadioButton newButton = new RadioButton(this);
                newButton.setText(generator[i][j] + "");
                newButton.setId(i * 100 + j);
                newButton.setWidth(180);
                newButton.setHeight(180);
                newButton.setOnClickListener(unused -> check(newButton.getText().toString()));
                tableChunk.addView(newButton);
            }
            table.addView(tableChunk);
        }
    }

    private int[][] generate(int line) {
        Random r = new Random();
        int[][] toReturn = new int[line][line];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line; j++) {
                boolean b = true;
                int newInteger = 0;
                while (b) {
                    b = false;
                    int newint = r.nextInt(line*line) + 1;
                    for (int k = 0; k <= i; k++) {
                        for (int l = 0; l < line; l++) {
                            if (newint == toReturn[k][l]) {
                                b = true;
                            }
                        }
                    }
                    newInteger = newint;
                    Log.e("ee", newInteger+ " " + i + " " + j);
                }
                toReturn[i][j] = newInteger;
            }
        }
        return toReturn;
    }

    private void check(String text) {
        int number = Integer.parseInt(text);
        Log.e("jjj", number + "");
        if (number == presentNumber + 1) {
            presentNumber++;
            TextView present = findViewById(R.id.presentNumber);
            present.setVisibility(View.VISIBLE);
            present.setText(presentNumber + "");
        }
        if (presentNumber == line * line) {
            onPause();
            Pass dialog = new Pass();
            dialog.levelPassed(4);
            dialog.show(getSupportFragmentManager(), "Pass");
        }
    }
    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("enter hint here. (e.g. Game6Activity");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
