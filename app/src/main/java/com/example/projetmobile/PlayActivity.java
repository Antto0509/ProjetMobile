package com.example.projetmobile;

import android.util.Log;
import android.content.Intent;
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
import java.math.BigDecimal;
import java.math.RoundingMode;

import android.view.View;

import com.example.projetmobile.database.ScoreBaseHelper;
import com.example.projetmobile.database.ScoreDao;
import com.example.projetmobile.entities.Score;

public class PlayActivity extends AppCompatActivity {
    // Pseudo saisi par l'utilisateur
    private String userPseudo;
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

    private ScoreDao scoreDao;

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

        // Récupérer le pseudo passé depuis MainActivity
        userPseudo = getIntent().getStringExtra("pseudo");

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
        updateValidationButtonState();

        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "db", 1));
    }

    // Désactiver le bouton de validation si aucun résultat n'est saisi
    public void updateValidationButtonState(){
        String resultText = textViewResult.getText().toString().trim();
        button_validate.setEnabled(!resultText.isEmpty());
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
        String currentText = textViewResult.getText().toString();

        // Vérifier si la longueur actuelle plus le nouveau caractère dépasse 12
        if (currentText.length() < 12) {
            textViewResult.setText(currentText + number);
        }
        updateValidationButtonState();
    }

    // Supprimer le calcul saisi
    private void deleteCalculation(){
        textViewResult.setText("");
        updateValidationButtonState();
    }

    // Récupérer le résultat du calcul généré
    private double getCalculationResult() {
        BigDecimal result;
        BigDecimal first = BigDecimal.valueOf(firstNumber);
        BigDecimal second = BigDecimal.valueOf(secondNumber);

        switch (operator) {
            case "+":
                result = first.add(second);
                break;
            case "-":
                result = first.subtract(second);
                break;
            case "x":
                result = first.multiply(second);
                break;
            case "/":
                // Si le second nombre est zéro, éviter la division par zéro
                if (second.compareTo(BigDecimal.ZERO) == 0) {
                    textViewCalculation.setText("Division par zéro");
                    return 0;
                }
                result = first.divide(second, 2, RoundingMode.HALF_UP);
                break;
            default:
                textViewCalculation.setText("Opérateur inconnu");
                return 0;
        }

        // Formater le résultat pour avoir au maximum deux chiffres après la virgule
        String formattedResult = result.setScale(2, RoundingMode.HALF_UP).toString();

        // Retourner la valeur formatée en tant que double
        return Double.parseDouble(formattedResult);
    }

    // Vérifier si la réponse est correcte
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
                    saveToDatabase();
                    // Redirection vers ScoreActivity
                    Intent intent = new Intent(PlayActivity.this, ScoreActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    Intent intent2 = new Intent(PlayActivity.this, MainActivity.class);
                    startActivity(intent2);
                    finish();
            }
            lifePoint--;
        }
    }

    // Actions à réaliser lors de la validation du calcul
    private void validateCalculation() {
        String userInput = textViewResult.getText().toString().trim();

        // Vérifier si l'entrée utilisateur est un nombre décimal valide
        if (isValidDecimal(userInput)) {
            double result = getCalculationResult();
            CheckAnswer(result);
            generateCalculation();
            deleteCalculation();
        }
    }

    // Méthode pour valider si une chaîne est un nombre décimal valide
    private boolean isValidDecimal(String input) {
        // Vérifier si la chaîne peut être convertie en double
        try {
            double number = Double.parseDouble(input);
            // Vérifier si le nombre est fini (pas infini ou NaN)
            return Double.isFinite(number);
        } catch (NumberFormatException e) {
            return false; // La chaîne n'est pas un nombre valide
        }
    }

    private void saveToDatabase() {
        // Création un objet Score avec le pseudo de l'utilisateur et le score actuel
        Score scoreEntity = new Score();
        scoreEntity.setPseudo(userPseudo);
        scoreEntity.setScore(score);

        // Insérer le score dans la base de données
        scoreDao.create(scoreEntity);
    }


    /*
    NOTES PERSO :
    résultat arrondi au centieme près. pour 2.857, res = 2.86
     */
}