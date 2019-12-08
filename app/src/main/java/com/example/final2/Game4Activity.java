package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class Game4Activity extends AppCompatActivity {

    /** hint button. **/
    private Button hintButton;
    private final int line = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

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
        LinearLayout table = findViewById(R.id.Table);
        int[][] generator = generate(line);
        for (int i = 0; i < line; i++) {
            Log.e("new", "this is " + i);
            View rowChunk = getLayoutInflater().inflate(R.layout.chunk_game4, table, false);
            Button bt1 = rowChunk.findViewById(R.id.bt1);
            Button bt2 = rowChunk.findViewById(R.id.bt2);
            Button bt3 = rowChunk.findViewById(R.id.bt3);
            Button bt4 = rowChunk.findViewById(R.id.bt4);
            Button bt5 = rowChunk.findViewById(R.id.bt5);
            Button bt6 = rowChunk.findViewById(R.id.bt6);
            //TableLayout tableChunk = rowChunk.findViewById(R.id.Tablechunk);
            bt1.setText(generator[i][0] + "");
            bt2.setText(generator[i][1] + "");
            bt3.setText(generator[i][2] + "");
            bt4.setText(generator[i][3] + "");
            bt5.setText(generator[i][4] + "");
            bt6.setText(generator[i][5] + "");
            table.addView(rowChunk);
        }
    }

    private int[][] generate(int line) {
        Random r = new Random();
        int[][] toReturn = new int[line][line];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line; j++) {
                toReturn[i][j] = i * 6 + j + 1;
            }
        }
        return toReturn;
    }
    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("enter hint here. (e.g. Game6Activity");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
