package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

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
        startGame.setOnClickListener(unused -> getToNewGamePage());
        Button continueGame = findViewById(R.id.continueGame);
        continueGame.setOnClickListener(unused -> getToGamePage());

    }
    private void getToNewGamePage() {
        CatalogActivity.resetGameList();
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
        finish();
    }

    private void getToGamePage() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
        finish();
    }
}
