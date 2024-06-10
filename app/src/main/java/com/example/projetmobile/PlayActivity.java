package com.example.projetmobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayActivity extends AppCompatActivity {
    // TextView pour les calculs générés et la saisie
    private TextView textViewCalculation;
    private TextView textViewResult;
    // Boutons de saisies
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;

    // Valeur minimale et maximale générée pour un calcul
    private int min = 1;
    private int max = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewCalculation = findViewById(R.id.textView_Calculation);
        textViewResult = findViewById(R.id.textView_Result);

        button_0 = findViewById(R.id.btn_0);
        button_1 = findViewById(R.id.btn_1);
        button_2 = findViewById(R.id.btn_2);
        button_3 = findViewById(R.id.btn_3);
        button_4 = findViewById(R.id.btn_4);
        button_5 = findViewById(R.id.btn_5);
        button_6 = findViewById(R.id.btn_6);
        button_7 = findViewById(R.id.btn_7);
        button_8 = findViewById(R.id.btn_8);
        button_9 = findViewById(R.id.btn_9);

        button_0.setOnClickListener(view -> pressNumber("0"));
        button_1.setOnClickListener(view -> pressNumber("1"));
        button_2.setOnClickListener(view -> pressNumber("2"));
        button_3.setOnClickListener(view -> pressNumber("3"));
        button_4.setOnClickListener(view -> pressNumber("4"));
        button_5.setOnClickListener(view -> pressNumber("5"));
        button_6.setOnClickListener(view -> pressNumber("6"));
        button_7.setOnClickListener(view -> pressNumber("7"));
        button_8.setOnClickListener(view -> pressNumber("8"));
        button_9.setOnClickListener(view -> pressNumber("9"));
    }

    private void pressNumber(String value){
        addNumber(value);
    }
    private void addNumber(String number){
        textViewResult.setText(textViewResult.getText()+number);
    }
}