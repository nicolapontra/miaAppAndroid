package com.example.nicola.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudenteDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Studente.db";

    private static final String STUDENTE_TABLE_NAME = "SchedaStudente";

    //colonne della tabella
    private static final String COLONNA_ID = "idStudente";
    private static final String COLONNA_NOME = "nome";
    private static final String COLONNA_COGNOME = "cognome";
    private static final String COLONNA_NASCITA = "dataNascita";
    private static final String COLONNA_SITOWEB = "sitoweb";
    private static final String COLONNA_MASCHIO = "maschio";
    private static final String COLONNA_FEMMINA = "femmina";
    private static final String COLONNA_ESAMIOK = "esamiok";
    private static final String COLONNA_ESAMIDAFARE = "esamidafare";
    private static final String COLONNA_MEDIA = "media";
    private static final String COLONNA_RATING = "rating";
    private static final String COLONNA_NUM1 = "num1";
    private static final String COLONNA_NUM2 = "num2";
    private static final String COLONNA_NUM3 = "num3";

    //questa stringa contiene il comando per la creazione della tabella studente
    private static final String STUDENTE_TABLE_CREATE = "CREATE TABLE " + STUDENTE_TABLE_NAME + "("+
            COLONNA_ID + " TEXT PRIMARY KEY," +
            COLONNA_NOME + " TEXT," +
            COLONNA_COGNOME + " TEXT," +
            COLONNA_NASCITA + " TEXT," +
            COLONNA_SITOWEB + " TEXT," +
            COLONNA_MASCHIO + " INTEGER," +
            COLONNA_FEMMINA + " INTEGER," +
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
