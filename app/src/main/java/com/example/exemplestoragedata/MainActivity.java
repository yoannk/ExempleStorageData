package com.example.exemplestoragedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Button btnSaveInternalStorage = findViewById(R.id.btnSaveInternalStorage);

        btnSaveInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomFichier = "FichierData.txt";

                String data = "Bonjour toto !";

                FileOutputStream fos;

                try {
                    // on ouvre un fichier pour écrire à l'intérieur
                    fos = openFileOutput(nomFichier, MODE_PRIVATE);
                    // on écrit nos données
                    fos.write(data.getBytes());
                    // on ferme le fichier
                    fos.close();
                } catch (FileNotFoundException e) {
                    Log.e("Erreur Data", e.getMessage());
                } catch (IOException e) {
                    Log.e("Erreur Data", e.getMessage());
                }
            }
        });
    }
}
