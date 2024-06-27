package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText InputPseudo;
    private Button PlayButton;
    private Button ScoreButton;
    private Button AboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        InputPseudo = findViewById(R.id.InputPseudo);
        PlayButton = findViewById(R.id.PlayButton);
        PlayButton.setOnClickListener(v -> {
            // Récupérer le pseudo entré dans EditText InputPseudo
            String pseudo = InputPseudo.getText().toString().trim();

            // Vérifier la longueur du pseudo
            if (pseudo.length() > 12) {
                // Afficher un message d'erreur si le pseudo est trop long
                InputPseudo.setError("Pseudo trop long, maximum 12 caractères");
            } else {
                if (pseudo.length() < 3) {
                    // Afficher un message d'erreur si le pseudo est trop long
                    InputPseudo.setError("Pseudo trop court, minimum 3 caractères");
                }else{
                    // Si la taille est acceptable, démarrer PlayActivity
                    Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                    intent.putExtra("pseudo", pseudo);
                    startActivity(intent);
                }
            }
        });

        ScoreButton = findViewById(R.id.ScoreButton);
        ScoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
            startActivity(intent);
        });

        AboutButton = findViewById(R.id.AboutButton);
        AboutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}