package com.example.projetmobile;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.text.DecimalFormat;

import android.view.View;

import android.os.Handler;
import android.os.Looper;

public class PlayActivity extends AppCompatActivity {
    // TextView pour le score, les calculs générés et la saisie
    private TextView textViewScoreValue;
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
    private Button button_comma;
    private Button button_delete;
    private Button button_validate;

    // Valeur minimale et maximale générée pour un calcul
    private int min = 1;
    private int max = 20;
    // Score et points de vie
    private int score = 0;
    private int lifePoint = 3;
    // Valeurs composant le calcul à résoudre
    private int firstNumber;
    private int secondNumber;
    private String operator;
    // Gestion des images
    private ImageView image_heart1;
    private ImageView image_heart2;
    private ImageView image_heart3;


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

        textViewScoreValue = findViewById(R.id.textView_ScoreValue);
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
        button_comma = findViewById(R.id.btn_comma);
        button_delete = findViewById(R.id.btn_delete);
        button_validate = findViewById(R.id.btn_validate);

        image_heart1 = findViewById(R.id.img_heart1);
        image_heart2 = findViewById(R.id.img_heart2);
        image_heart3 = findViewById(R.id.img_heart3);

        button_0.setOnClickListener(view -> addValue("0"));
        button_1.setOnClickListener(view -> addValue("1"));
        button_2.setOnClickListener(view -> addValue("2"));
        button_3.setOnClickListener(view -> addValue("3"));
        button_4.setOnClickListener(view -> addValue("4"));
        button_5.setOnClickListener(view -> addValue("5"));
        button_6.setOnClickListener(view -> addValue("6"));
        button_7.setOnClickListener(view -> addValue("7"));
        button_8.setOnClickListener(view -> addValue("8"));
        button_9.setOnClickListener(view -> addValue("9"));
        button_comma.setOnClickListener(view -> addValue("."));
        button_delete.setOnClickListener(view -> deleteCalculation());
        button_validate.setOnClickListener(view -> validateCalculation());

        generateCalculation();
    }

    // Générer un nombre aléatoire
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    // Générer un opérateur aléatoire
    public String getRandomOperator() {
        String[] operators = {"+", "-", "x", "/"};
        Random random = new Random();
        int index = random.nextInt(operators.length);
        return operators[index];
    }

    // Affiche le calcul avec les nombres aléatoires générés
    private void generateCalculation(){
        firstNumber = getRandomNumber(min, max);
        secondNumber = getRandomNumber(min, max);
        operator = getRandomOperator();
        if (firstNumber < secondNumber){
            int save = firstNumber;
            firstNumber = secondNumber;
            secondNumber = save;
        }
        textViewCalculation.setText(firstNumber + " " + operator + " " + secondNumber);
    }

    // Ajouter le chiffre au clique sur le clavier numérique
    private void addValue(String number){
        textViewResult.setText(textViewResult.getText()+number);
    }

    // Supprimer le calcul saisi
    private void deleteCalculation(){
        textViewResult.setText("");
    }

    // Actions à réaliser lors de la validation du calcul
    private void validateCalculation(){
        double result = getCalculationResult();
        CheckAnswer(result);
    }

    // Récupérer le résultat du calcul généré
    private double getCalculationResult(){
        double result;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "x":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = (double) firstNumber / secondNumber;
                break;
            default:
                textViewCalculation.setText("Opérateur inconnu");
                return 0;
        }

        // Formater le résultat pour avoir au maximum deux chiffres après la virgule
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedResult = df.format(result);

        textViewCalculation.setText(firstNumber + " " + operator + " " + secondNumber + " = " + formattedResult);


        // Retourner la valeur formatée en tant que double
        return Double.parseDouble(formattedResult);
    }

    // Vérifier l
    private void CheckAnswer(double result){
        String userInput = textViewResult.getText().toString().trim();
        double userResult = Double.parseDouble(userInput);

        // Si l'utilisateur a bien répondu au calcul
        if(Double.compare(result, userResult) == 0){
            score++;
            textViewScoreValue.setText(String.valueOf(score));
        }
        else{
            switch (lifePoint) {
                case 3:
                    image_heart3.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    image_heart2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    image_heart1.setVisibility(View.INVISIBLE);
                    break;
                default:

            }
            lifePoint--;
        }
    }

    // Marquer un temps d'arrêt de 3 secondes
    /*private void timePause(Runnable task) {
        Utils.waitAndExecute(3000, task);
    }*/


    /*
    NOTES PERSO :
    RAF :
    - VERIFIER SI LA CALCUL USER = BON RESULTAT
    - CHANGER DE CALCUL

    résultat arrondi au centieme près. pour 2.857, res = 2.86
     */

}