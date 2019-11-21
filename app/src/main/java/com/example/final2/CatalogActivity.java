package com.example.final2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This is the page for all games.
 */
public class CatalogActivity extends AppCompatActivity {
    final int countGame = 9;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        /*LinearLayout gameList = findViewById(R.id.GameList);
        for (int i = 0; i < countGame; i++) {
            View GameChunk = getLayoutInflater().inflate(R.layout.chunk_game, gameList, false);
            ImageButton gameButton = GameChunk.findViewById(R.id.gameButton);
            int index = i;
            gameButton.setOnClickListener(unused -> enterGame(index));
        }*/
        ImageButton game1 = findViewById(R.id.game1);
        game1.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel1.class);
            startActivity(intent);
        });
        ImageButton game2 = findViewById(R.id.game2);
        game2.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel2.class);
            startActivity(intent);
        });
        ImageButton game3 = findViewById(R.id.game3);
        game3.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel3.class);
            startActivity(intent);
        });
        ImageButton game4 = findViewById(R.id.game4);
        game4.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel4.class);
            startActivity(intent);
        });
        ImageButton game5 = findViewById(R.id.game5);
        game5.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel5.class);
            startActivity(intent);
        });
        ImageButton game6 = findViewById(R.id.game6);
        game6.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel6.class);
            startActivity(intent);
        });
        ImageButton game7 = findViewById(R.id.game7);
        game7.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel7.class);
            startActivity(intent);
        });
        ImageButton game8 = findViewById(R.id.game8);
        game8.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel8.class);
            startActivity(intent);
        });
        ImageButton game9 = findViewById(R.id.game9);
        game9.setOnClickListener(unused -> {
            Intent intent = new Intent(this, GameLevel9.class);
            startActivity(intent);
        });
    }
}
