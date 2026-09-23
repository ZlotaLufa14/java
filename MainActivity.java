package com.example.formularz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText imie;
    private EditText nazwisko;
    private EditText email;
    private EditText haslo;
    private Button przeslij;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imie = findViewById(R.id.imie);
        String imie_tekst = String.valueOf(imie);

        nazwisko = findViewById(R.id.nazwisko);
        String nazwisko_tekst = String.valueOf(nazwisko);

        email = findViewById(R.id.email);
        String email_tekst = String.valueOf(email);

        haslo = findViewById(R.id.haslo);
        String haslo_tekst = String.valueOf(haslo);

        przeslij = findViewById(R.id.przeslij);

        przeslij.setOnClickListener(v -> {
            if(sprawdzPustosc(imie_tekst) && sprawdzPustosc(nazwisko_tekst) && sprawdzEmail(email_tekst) && sprawdzHaslo(haslo_tekst)) {
                Toast.makeText(this, "Jest git", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Podano złe dane", Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean sprawdzPustosc(String element) {
        if(!element.isEmpty()) return true;
        return false;
    }

    public boolean sprawdzEmail(String element) {
        if(!element.contains("@") && !element.contains(".")) return false;
        return true;
    }

    public boolean sprawdzHaslo(String element) {
        if(element.length() < 8 || !element.matches(".*[a-z].*") || !element.matches(".*[A-Z].*") || !element.matches(".*[a-zA-z0-9].*")) return false;
        return true;
    }
}
