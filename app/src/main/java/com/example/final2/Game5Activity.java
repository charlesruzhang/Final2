package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** guessing words game.
 * guessing a word between 0 to 99
 * return the input is bigger or smaller than the generated random word
 * 7 chance to guess
 */

public class Game5Activity extends AppCompatActivity {
    private int answer;
    private int chance = 7;
    private int inputNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        answer = (int) (Math.random() * 100);
        TextView input = findViewById(R.id.input);
        TextView compare = findViewById(R.id.comparasion);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            try {
                inputNumber = Integer.valueOf(input.getText().toString());
                chance--;
                if (chance == 0) {
                    compare.setText("You've used up 7 chances...let's make another guess!");
                    answer = (int) (Math.random() * 100);
                    chance = 7;
                } else {
                    if (inputNumber > answer) {
                        compare.setText("I'm thinking about a smaller number...");
                    }
                    if (inputNumber < answer) {
                        compare.setText("I'm thinking about a bigger number...");
                    }
                    if (inputNumber == answer) {
                        compare.setText("This is what I'm thinking about!");
                    }
                }
            } catch (Exception e) {
                compare.setText("invalid input");
            }
        });
    }
}
