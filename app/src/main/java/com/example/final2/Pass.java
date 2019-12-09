package com.example.final2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Pass extends DialogFragment {
    private static final String TAG = "Pass";
    private Button returnButton;
    private Button continueButton;
    private int currentLevel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pass, container, false);
        returnButton = view.findViewById(R.id.returnToCatalog);
        continueButton = view.findViewById(R.id.continueButton);
        returnButton.setOnClickListener(v -> {
            AFactory.catalogActivity.passGame(currentLevel);
            getActivity().finish();
        });
        continueButton.setOnClickListener(v -> {
            AFactory.catalogActivity.passGame(currentLevel);
            getActivity().finish();
            if (AFactory.catalogActivity.getSize() == 0) {
                AFactory.catalogActivity.winGame();
                return;
            } else {
                int nextLevel = AFactory.catalogActivity.getLevel(0);
                if (nextLevel == 1) {
                    Intent intent = new Intent(inflater.getContext(), Game1Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 2) {
                    Intent intent = new Intent(inflater.getContext(), Game2Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 3) {
                    Intent intent = new Intent(inflater.getContext(), Game3Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 4) {
                    Intent intent = new Intent(inflater.getContext(), Game4Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 5) {
                    Intent intent = new Intent(inflater.getContext(), Game5Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 6) {
                    Intent intent = new Intent(inflater.getContext(), Game6Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 7) {
                    Intent intent = new Intent(inflater.getContext(), Game7Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 8) {
                    Intent intent = new Intent(inflater.getContext(), Game8Activity.class);
                    startActivity(intent);
                }
                if (nextLevel == 9) {
                    Intent intent = new Intent(inflater.getContext(), Game9Activity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    public void levelPassed(int a) {
        currentLevel = a;
    }

}