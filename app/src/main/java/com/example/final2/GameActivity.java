package com.example.final2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private int gameIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        gameIndex = intent.getIntExtra("gameIndex", -1);
    }
    private void gameEnd() {
        //writeFile(gameIndex);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yeah you did it!");
        builder.setNegativeButton("Next", (unused1, unused2) -> nextGame());
        builder.setPositiveButton("Back to Main Menu", (unused1, unused2) -> backToMenu());
        builder.create().show();
    }
    private void nextGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gameIndex", gameIndex + 1);
        startActivity(intent);
        finish();
    }
    private void backToMenu() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
    }
    public void writeFile(int index) throws IOException {
        String fileName = "finishedGames.txt";
        String res="";
        try {
            FileInputStream fin = openFileInput(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            //res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        // We omitted codes for writing and reading files.
        try {
            FileOutputStream output = openFileOutput(fileName, MODE_PRIVATE);
            String str = res + gameIndex + ": true ";
            byte[] bytes = str.getBytes();
            output.write(bytes);
            output.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void exit() {}

    private void hint() {}
}
