package com.example.projetmobile.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetmobile.entities.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends BaseEntity> {
    private final DataBaseHelper dbHelper;

    public BaseDao(DataBaseHelper helper){
        this.dbHelper = helper;
    }

    protected abstract String getTableName();
    protected abstract void putValues(ContentValues values, T entity);
    protected abstract T getEntity(Cursor cursor);

    public T create(T entity){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        putValues(values, entity);

        long newRowId = db.insert(getTableName(), null, values);
        entity.id = newRowId;
        return entity;
    }

    protected List<T> query(String selection, String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                getTableName(),
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List items = new ArrayList<T>();
        while(cursor.moveToNext()) {
            items.add(getEntity(cursor));

        }
        cursor.close();

        return items;
    }

    // Meilleur score
    public T getBestScore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select pseudo, score from " + getTableName() + " order by score desc limit 1", null);

        if (!cursor.moveToFirst()) {
            return null;
        }

        T score = this.getEntity(cursor);
        cursor.close();

        return score;
    }

    // Liste des scores
    public List<T> getScores() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select pseudo, score from " + getTableName() + " order by score desc", null);

        List items = new ArrayList<T>();
        while(cursor.moveToNext()) {
            items.add(getEntity(cursor));
        }
        cursor.close();

        return items;
    }
}