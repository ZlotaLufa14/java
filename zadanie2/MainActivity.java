package com.example.lista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText fileNameInput;
    private EditText edytor;
    private String currentFile = null;


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

        fileNameInput = findViewById(R.id.nazwa_pliku);
        edytor = findViewById(R.id.edytor);

        Button otworz = findViewById(R.id.otworz);
        Button zapisz = findViewById(R.id.otworz);
        Button usun = findViewById(R.id.usun);

        otworz.setOnClickListener(v -> { openFile(); });
        zapisz.setOnClickListener(v -> { saveFile(); });
        usun.setOnClickListener(v -> { deleteFile(); });


    }

    private void openFile() {
        String filename = fileNameInput.getText().toString().trim();
        if(filename.isEmpty()) {
            return;
        }

        try {
            edytor.setText(readFile((filename)));
            currentFile = filename;
        } catch (IOException e) {

        }
    }
    
    private String readFile(String fileName) throws IOException {
        File internal = new File(getFilesDir(), fileName);
        InputStream is;
        if(internal.exists()) {
            is = new FileInputStream(internal);
        } else {
            is = getAssets().open(fileName);
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private void saveFile() {
        if (currentFile == null) {
            return;
        }

        File file = new File(getFilesDir(), currentFile);
        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(edytor.getText().toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            
        }
    }

    private void deleteFile() {

    }
}
