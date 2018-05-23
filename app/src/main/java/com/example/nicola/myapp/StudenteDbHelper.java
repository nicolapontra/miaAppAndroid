package com.example.nicola.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudenteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Studente.db";

    public static final String STUDENTE_TABLE_NAME = "SchedaStudente";

    //colonne della tabella
    public static final String COLONNA_NOME = "nome";
    public static final String COLONNA_COGNOME = "cognome";
    public static final String COLONNA_NASCITA = "dataNascita";
    public static final String COLONNA_SITOWEB = "sitoweb";
    public static final String COLONNA_MASCHIO = "maschio";
    public static final String COLONNA_FEMMINA = "femmina";
    public static final String COLONNA_ESAMIOK = "esamiok";
    public static final String COLONNA_ESAMIDAFARE = "esamidafare";
    public static final String COLONNA_MEDIA = "media";
    public static final String COLONNA_RATING = "rating";
    public static final String COLONNA_NUM1 = "num1";
    public static final String COLONNA_NUM2 = "num2";
    public static final String COLONNA_NUM3 = "num3";

    //questa stringa contiene il comando per la creazione della tabella studente
    private static final String STUDENTE_TABLE_CREATE = "CREATE TABLE " + STUDENTE_TABLE_NAME + "("+
            COLONNA_NOME + " TEXT," +
            COLONNA_COGNOME + " TEXT," +
            COLONNA_NASCITA + " TEXT," +
            COLONNA_SITOWEB + " TEXT," +
            COLONNA_MASCHIO + " BOOLEAN," +
            COLONNA_FEMMINA + " BOOLEAN," +
            COLONNA_ESAMIOK + " TEXT," +
            COLONNA_ESAMIDAFARE + " TEXT," +
            COLONNA_MEDIA + " INTEGER," +
            COLONNA_RATING + " REAL," +
            COLONNA_NUM1 + " TEXT," +
            COLONNA_NUM2 + " TEXT," +
            COLONNA_NUM3 + " TEXT);";


    public StudenteDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENTE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
