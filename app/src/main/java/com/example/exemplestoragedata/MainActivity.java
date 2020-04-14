package com.example.exemplestoragedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Button btnSaveInternalStorage = findViewById(R.id.btnSaveInternalStorage);
        Button btnOpenInternalStorage = findViewById(R.id.btnOpenInternalStorage);

        btnSaveInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomFichier = "FichierData.txt";

                String data = "Bonjour toto !";

                try {
                    // on ouvre un fichier pour écrire à l'intérieur
                    FileOutputStream fos = openFileOutput(nomFichier, MODE_PRIVATE);
                    // on écrit nos données
                    fos.write(data.getBytes());
                    // on ferme le fichier
                    fos.close();
                } catch (FileNotFoundException e) {
                    Log.e("Erreur Save Data", e.getMessage());
                } catch (IOException e) {
                    Log.e("Erreur Save Data", e.getMessage());
                }
            }
        });

        btnOpenInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomFichier = "FichierData.txt";
                StringBuilder sb = new StringBuilder();

                try {
                    FileInputStream fis = openFileInput(nomFichier);
                    InputStreamReader inputStreamReader = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    inputStreamReader.close();
                } catch (FileNotFoundException e) {
                    Log.e("Erreur Open Data", e.getMessage());
                } catch (IOException e) {
                    Log.e("Erreur Open Data", e.getMessage());
                }

                Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
