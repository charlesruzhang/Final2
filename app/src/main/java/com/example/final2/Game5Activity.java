package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** guessing words game.
 * guessing a word between 0 and 99
 * return the input is bigger or smaller than the generated random word
 * 8 chance to guess
 */

public class Game5Activity extends AppCompatActivity {
    private int answer;
    private int chance = 8;
    private int inputNumber;
    /** hint button. **/
    private Button hintButton;
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
                    compare.setText("You've used up 8 chances...let's make another guess!");
                    answer = (int) (Math.random() * 100);
                    chance = 8;
                } else {
                    if (inputNumber > answer) {
                        compare.setText("I'm thinking about a smaller number...");
                    }
                    if (inputNumber < answer) {
                        compare.setText("I'm thinking about a bigger number...");
                    }
                    if (inputNumber == answer) {
                        compare.setText("This is what I'm thinking about!");
                        int yourGameLevel = 5;
                        Pass dialog = new Pass();
                        dialog.levelPassed(yourGameLevel);
                        dialog.show(getSupportFragmentManager(), "Pass");
                    }
                }
            } catch (Exception e) {
                compare.setText("invalid input");
            }
        });
        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());
    }

    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("the number is between 0 and 99.");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
