package com.example.projetmobile.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.projetmobile.entities.Score;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoreDao extends BaseDao<Score> {
    public static final String tableName = "lstScore";
    public static final String score = "score";
    public static final String pseudo = "pseudo";

    public ScoreDao(ScoreBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(score, entity.getScore());
        values.put(pseudo, entity.getPseudo());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        Score toReturn = new Score();
        Integer indexScore = cursor.getColumnIndex(score);
        Integer indexPseudo = cursor.getColumnIndex(pseudo);
        toReturn.setScore(cursor.getInt(indexScore));
        toReturn.setPseudo(cursor.getString(indexPseudo));
        return toReturn;
    }
}
