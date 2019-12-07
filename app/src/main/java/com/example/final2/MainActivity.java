package com.example.final2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the first page of the game.
 */
public class MainActivity extends AppCompatActivity {
    private AnimationDrawable title;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(unused -> getToNewGamePage());
        Button continueGame = findViewById(R.id.continueGame);
        continueGame.setOnClickListener(unused -> getToGamePage());
        ImageView titleAnimation = (ImageView) findViewById(R.id.title);
        titleAnimation.setBackgroundResource(R.drawable.title_animation);
        title = (AnimationDrawable) titleAnimation.getBackground();
        //title.start();

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        title.start();
    }
}
