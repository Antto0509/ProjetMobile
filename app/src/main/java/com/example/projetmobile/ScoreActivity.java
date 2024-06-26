package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetmobile.database.ScoreBaseHelper;
import com.example.projetmobile.database.ScoreDao;
import com.example.projetmobile.entities.Score;

public class ScoreActivity extends AppCompatActivity {
    private ScoreDao scoreDao;
    private TextView bestScore;
    private TextView scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Objectif de cette page : afficher le meilleur score puis la liste des scores (avec pseudo) enregistrés

        // On récupère les textViews
        bestScore = findViewById(R.id.textHighScore);
        scores = findViewById(R.id.textScores);

        // On récupère le meilleur score
        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "db", 1));
        Score best = scoreDao.getBestScore();
        if (best != null) {
            String HighScoreFormat = "🌟 " + best.getPseudo() + " : " + best.getScore() + " points 🌟";
            bestScore.setText(String.format(getString(R.string.HighScore), HighScoreFormat));
        } else {
            bestScore.setText("Aucun score enregistré");
        }

        // On récupère la liste des scores
        StringBuilder scoresList = new StringBuilder();
        Integer i = 1;
        if (scoreDao.getScores().isEmpty()) {
            scoresList.append("Aucun score enregistré");
        } else {
            for (Score score : scoreDao.getScores()) {
                String scoresFormat = "🥇 " + i + ". " + score.getPseudo() + " : " + score.getScore() + " points 🥇\n";
                scoresList.append(scoresFormat);
                i++;
            }
        }
        scores.setText(scoresList.toString());
    }
}