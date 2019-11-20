package com.example.final2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This is the page for all games.
 */
public class CatalogActivity extends AppCompatActivity {
    final int countGame = 8;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        LinearLayout gameList = findViewById(R.id.GameList);
        for (int i = 0; i < countGame; i++) {
            View GameChunk = getLayoutInflater().inflate(R.layout.chunk_game, gameList, false);
            ImageButton gameButton = GameChunk.findViewById(R.id.gameButton);
            int index = i;
            gameButton.setOnClickListener(unused -> enterGame(index));
        }
    }
    private void enterGame(int index) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gameIndex", index);
        startActivity(intent);
    }
}
