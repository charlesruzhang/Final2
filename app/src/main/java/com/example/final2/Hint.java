package com.example.final2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Hint extends DialogFragment {
    private static final String TAG = "Hint";
    private Button returnButton;
    private TextView hintText;
    private String hint;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_hint, container, false);
        hintText = view.findViewById(R.id.hintText);
        hintText.setText(hint);
        returnButton = view.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> {
            getDialog().dismiss();
        });
        return view;
    }

    public void addHint(String h) {
        hint = h;
    }
}
