package com.example.projetmobile.database;

import android.content.Context;

public class ScoreBaseHelper extends DataBaseHelper {
    public ScoreBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, "db", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + ScoreDao.tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                ScoreDao.pseudo + " VARCHAR(50) NOT NULL," +
                ScoreDao.score + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS " + ScoreDao.tableName;
    }


}
