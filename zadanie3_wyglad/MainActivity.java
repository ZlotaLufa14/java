package com.example.zadanie_wyglad;

import android.graphics.Color;                    // narzędzia do kolorów: parseColor(), alpha()
import android.os.Bundle;                         // paczka z zapisanym stanem aktywności (onCreate)
import android.text.Editable;                     // edytowalny tekst z pola EditText (parametr afterTextChanged)
import android.text.TextWatcher;                  // interfejs "obserwatora" zmian tekstu
import android.view.View;
import android.widget.Button;
import android.widget.EditText;                   // pole do wpisywania tekstu
import android.widget.LinearLayout;               // typ naszego panelu
import android.widget.RadioGroup;                 // grupa przycisków radiowych
import android.widget.SeekBar;                    // suwak
import android.widget.TextView;                   // napis

import androidx.appcompat.app.AppCompatActivity;  // bazowa klasa ekranu (zgodna ze starszymi Androidami)
import androidx.core.graphics.ColorUtils;         // setAlphaComponent(): podmiana kanału alfa w kolorze

public class MainActivity extends AppCompatActivity {
    private static final int BASE_COLOR = Color.parseColor("#FFFFFF");
    private static final int KWADRAT_BASE_COLOR = Color.parseColor("#AA00BB");

    private TextView tvInfo;
    private SeekBar seekAlpha;
    private View kwadrat;
    private Button dodaj10;
    private Button odejmij10;
    private int kwadratAlpha = 255;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);
        seekAlpha = findViewById(R.id.seekAlpha);
        kwadrat = findViewById(R.id.kwadrat);
        dodaj10 = findViewById(R.id.dodaj10);
        odejmij10 = findViewById(R.id.odejmij10);


        seekAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    applyAlpha(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        dodaj10.setOnClickListener(v -> {
            kwadratAlpha = Math.min(255, kwadratAlpha + 10);
            kwadrat.setBackgroundColor(ColorUtils.setAlphaComponent(KWADRAT_BASE_COLOR, kwadratAlpha));
        });

        odejmij10.setOnClickListener(v -> {
            kwadratAlpha = Math.max(0, kwadratAlpha - 10);
            kwadrat.setBackgroundColor(ColorUtils.setAlphaComponent(KWADRAT_BASE_COLOR, kwadratAlpha));
        });

    }

    private void applyAlpha(int percent) {
        int alpha = Color.alpha(BASE_COLOR) * percent / 100;
        tvInfo.setTextColor(ColorUtils.setAlphaComponent(BASE_COLOR, alpha));
        tvInfo.setText(percent + "%");
    }
}