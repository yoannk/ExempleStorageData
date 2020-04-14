package com.example.exemplestoragedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exemplestoragedata.Entities.Adherent;
import com.example.exemplestoragedata.Utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Context context;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        gson = new Gson();

        Button btnSaveInternalStorage = findViewById(R.id.btnSaveInternalStorage);
        Button btnOpenInternalStorage = findViewById(R.id.btnOpenInternalStorage);

        btnSaveInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adherent adherent = new Adherent();
                adherent.setNom("Toto");
                adherent.setPrenom("Titi");

                String data = gson.toJson(adherent);

                try {
                    // on ouvre un fichier pour écrire à l'intérieur
                    FileOutputStream fos = openFileOutput(Constants.NOM_FICHIER, MODE_PRIVATE);
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
                StringBuilder sb = new StringBuilder();


                try {
                    FileInputStream fis = openFileInput(Constants.NOM_FICHIER);
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

                try {
                    Adherent adherent = gson.fromJson(sb.toString(), Adherent.class);
                    Toast.makeText(context, "Bonjour " + adherent.getPrenom() + " " + adherent.getNom(), Toast.LENGTH_SHORT).show();
                } catch (JsonSyntaxException e) {
                    Toast.makeText(context, "JSON malformé", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
