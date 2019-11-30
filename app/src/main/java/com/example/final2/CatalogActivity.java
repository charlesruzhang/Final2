package com.example.final2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This is the page for all games.
 */
public class CatalogActivity extends AppCompatActivity {
    //final int countGame = 9;
    private static List<String> unPlayedGames = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
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
            Intent intent = new Intent(this, Game1Activity.class);
            startActivity(intent);
        });

        ImageButton game2 = findViewById(R.id.game2);
        game2.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game2Activity.class);
            startActivity(intent);
        });

        ImageButton game3 = findViewById(R.id.game3);
        game3.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game3Activity.class);
            startActivity(intent);
        });

        ImageButton game4 = findViewById(R.id.game4);
        game4.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game4Activity.class);
            startActivity(intent);
        });

        ImageButton game5 = findViewById(R.id.game5);
        game5.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game5Activity.class);
            startActivity(intent);
        });

        ImageButton game6 = findViewById(R.id.game6);
        game6.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game6Activity.class);
            startActivity(intent);
        });

        ImageButton game7 = findViewById(R.id.game7);
        game7.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game7Activity.class);
            startActivity(intent);
        });

        ImageButton game8 = findViewById(R.id.game8);
        game8.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game8Activity.class);
            startActivity(intent);
        });

        ImageButton game9 = findViewById(R.id.game9);
        game9.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game9Activity.class);
            startActivity(intent);
        });
    }

    public static void resetGameList() {
        unPlayedGames = new ArrayList<>(
                Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
    }

    public static void passGame(int gameLevel) {
        String index = "" + gameLevel;
        unPlayedGames.remove(index);
    }

    public static int getSize() {
        return unPlayedGames.size();
    }

    public static int getLevel(int index) {
        Integer a = Integer.valueOf(unPlayedGames.get(index));
        return a;
    }
}
