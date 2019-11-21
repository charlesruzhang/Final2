package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

/**
 * This is the first page of the game.
 */
public class MainActivity extends AppCompatActivity {
    final int countGame = 8;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGame = findViewById(R.id.startGame);
        startGame.setText("Enter Game");
        startGame.setOnClickListener(unused -> getToGamePage());


    }
    private void getToGamePage() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
        finish();
    }
    // CS 125 hahha
}
