package com.fiek.transfuzioni_gjakut.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;

    String createTableUser = "CREATE TABLE IF NOT EXISTS \"user\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"fullname\"\tTEXT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"phone\"\tINTEGER,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" )\n" +
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }


    public void inserUser(ContentValues contentValues){
        getWritableDatabase().insert("user","", contentValues );
    }

    public boolean isLoginValid(String email, String password){

        String sql = "Select count(*) from user where email='" +email+"' and passwrd='" +password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if (l ==1){
            return true;
        }
        else{
            return false;
        }

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
