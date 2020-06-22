package com.fiek.transfuzioni_gjakut.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String PerdoruesitTable = "perdoruesit";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "myDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQuery = "create table "+PerdoruesitTable+" (" +
                User.ID + " integer primary key autoincrement,"+
                User.Emri +" text not null,"+
                User.Mbiemri+" text not null,"+
                User.Adresa+" text not null,"+
                User.Email+" text,"+
                User.Username+" text not null,"+
                User.Password+" text not null"+
                ")";

        db.execSQL(strQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
