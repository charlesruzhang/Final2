package com.example.final2;

import android.content.Intent;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private ImageButton game1;
    private ImageButton game2;
    private ImageButton game3;
    private ImageButton game4;
    private ImageButton game5;
    private ImageButton game6;
    private ImageButton game7;
    private ImageButton game8;
    private ImageButton game9;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        AFactory.catalogActivity = this;
        /*LinearLayout gameList = findViewById(R.id.GameList);
        for (int i = 0; i < countGame; i++) {
            View GameChunk = getLayoutInflater().inflate(R.layout.chunk_game4, gameList, false);
            ImageButton gameButton = GameChunk.findViewById(R.id.gameButton);
            int index = i;
            gameButton.setOnClickListener(unused -> enterGame(index));
        }*/
        start();
    }
    private void start() {
        game1 = findViewById(R.id.game1);
        game1.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game1Activity.class);
            startActivity(intent);
        });

        game2 = findViewById(R.id.game2);
        game2.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game2Activity.class);
            startActivity(intent);
        });

        game3 = findViewById(R.id.game3);
        game3.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game3Activity.class);
            startActivity(intent);
        });

        game4 = findViewById(R.id.game4);
        game4.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game4Activity.class);
            startActivity(intent);
        });

        game5 = findViewById(R.id.game5);
        game5.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game5Activity.class);
            startActivity(intent);
        });
        if (unPlayedGames.indexOf("5") == -1) {
            game5.setBackgroundResource(R.drawable.f_05);
        }
        game6 = findViewById(R.id.game6);
        game6.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game6Activity.class);
            startActivity(intent);
        });
        game7 = findViewById(R.id.game7);
        game7.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game7Activity.class);
            startActivity(intent);
        });

        game8 = findViewById(R.id.game8);
        game8.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game8Activity.class);
            startActivity(intent);
        });

        game9 = findViewById(R.id.game9);
        game9.setOnClickListener(unused -> {
            Intent intent = new Intent(this, Game9Activity.class);
            startActivity(intent);
        });
        if (unPlayedGames.indexOf("1") == -1) {
            game1.setImageResource(R.drawable.f_01);
        }
        if (unPlayedGames.indexOf("2") == -1) {
            game2.setImageResource(R.drawable.f_02);
        }
        if (unPlayedGames.indexOf("3") == -1) {
            game3.setImageResource(R.drawable.f_03);
        }
        if (unPlayedGames.indexOf("4") == -1) {
            game4.setImageResource(R.drawable.f_04);
        }
        if (unPlayedGames.indexOf("5") == -1) {
            game5.setImageResource(R.drawable.f_05);
        }
        if (unPlayedGames.indexOf("6") == -1) {
            game6.setImageResource(R.drawable.f_06);
        }
        if (unPlayedGames.indexOf("7") == -1) {
            game7.setImageResource(R.drawable.f_07);
        }
        if (unPlayedGames.indexOf("8") == -1) {
            game8.setImageResource(R.drawable.f_08);
        }
        if (unPlayedGames.indexOf("9") == -1) {
            game9.setImageResource(R.drawable.f_09);
        }
    }

    public void resetGameList() {
        unPlayedGames = new ArrayList<>(
                Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
    }

    public void passGame(int gameLevel) {
        String index = "" + gameLevel;
        Log.e("333", "hi");
        unPlayedGames.remove(index);
        start();
    }

    public int getSize() {
        return unPlayedGames.size();
    }

    public int getLevel(int index) {
        Integer a = Integer.valueOf(unPlayedGames.get(index));
        return a;
    }
    public void winGame() {
        TextView instruction = findViewById(R.id.instruction);
        instruction.setText("Yeah, you have completed all the games!");
    }
}
