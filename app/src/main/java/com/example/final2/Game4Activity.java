package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Game4Activity extends AppCompatActivity {

    /** hint button. **/
    private Button hintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());
    }

    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("enter hint here. (e.g. Game6Activity");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
}
