package com.example.projetmobile;

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
    private TextView textView_NbScore;
    private TextView textView_DernierScore;

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
        textView_NbScore = findViewById(R.id.textViewNbScore);
        textView_DernierScore = findViewById(R.id.textViewDernierScore);
        scoreDao = new ScoreDao(new ScoreBaseHelper(this, "db", 1));
        Integer nbScore = (int) scoreDao.count();
        Score dernierScore = scoreDao.lastOrNull();
        if (dernierScore != null) {
            String scoreFormat = dernierScore.getScore()
                    + " " + dernierScore.getPseudo();
            textView_DernierScore.setText(String.format(getString(R.string.TEXT_TEXTVIEW_DERNIER_SCORE), scoreFormat));
        }
        textView_NbScore.setText(String.format(getString(R.string.TEXT_TEXTVIEW_NB_SCORE), nbScore));
    }
}