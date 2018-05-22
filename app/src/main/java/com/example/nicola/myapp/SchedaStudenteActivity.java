package com.example.nicola.myapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import java.util.Calendar;

public class SchedaStudenteActivity extends AppCompatActivity {

    EditText editNome;
    EditText editCognome;
    EditText editDataNasc;
    EditText editSito;
    EditText editEsamiok;
    EditText editEsamiDaFare;
    EditText editMedia;
    EditText editCell1;
    EditText editCell2;
    EditText editCell3;
    RadioButton maschio;
    RadioButton femmina;
    RatingBar rating;
    ToggleButton modifica;

    Button changeData;
    static final int DATE_DIALOG_ID = 0;
    final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR);
    private int mMonth = c.get(Calendar.MONTH);
    private int mDay = c.get(Calendar.DAY_OF_MONTH);

    //---------------------------------------------------------------------
    private static final String SCHEDA_PREF = "com.example.nicola.myapp.SCHEDASTUDENTE_PREF";

    //istanza del db
    StudenteDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_studente);

        editNome = (EditText) findViewById(R.id.editNome);
        editNome.setFocusable(false);
        editCognome = (EditText) findViewById(R.id.editCognome);
        editCognome.setFocusable(false);
        editDataNasc = (EditText) findViewById(R.id.editNascita);
        editDataNasc.setFocusable(false);
        editSito = (EditText) findViewById(R.id.editSito);
        editSito.setFocusable(false);
        editEsamiok = (EditText) findViewById(R.id.editEsami1);
        editEsamiok.setFocusable(false);
        editEsamiDaFare = (EditText) findViewById(R.id.editEsami2);
        editEsamiDaFare.setFocusable(false);
        editMedia = (EditText) findViewById(R.id.editMedia);
        editMedia.setFocusable(false);
        editCell1 = (EditText) findViewById(R.id.editCell1);
        editCell1.setFocusable(false);
        editCell2 = (EditText) findViewById(R.id.editCell2);
        editCell2.setFocusable(false);
        editCell3 = (EditText) findViewById(R.id.editCell3);
        editCell3.setFocusable(false);
        maschio = (RadioButton) findViewById(R.id.radiobtn1);
        maschio.setClickable(false);
        femmina = (RadioButton) findViewById(R.id.radiobtn2);
        femmina.setClickable(false);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        rating.setClickable(false);

        modifica = (ToggleButton) findViewById(R.id.toggleButton);
        modifica.setOnClickListener(abilitaconferma);

        changeData = (Button) findViewById(R.id.btnChangeData);
        // add a click listener to the button
        changeData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        //------------------------------------------------------------------------------------------
       /* SharedPreferences preferences = getSharedPreferences(SCHEDA_PREF, Context.MODE_PRIVATE);
        editNome.setText(preferences.getString("nome", ""));
        editCognome.setText(preferences.getString("cognome", ""));
        editDataNasc.setText(preferences.getString("datanascita", ""));
        editSito.setText(preferences.getString("sitoweb", ""));
        editEsamiok.setText(preferences.getString("esamisvolti", ""));
        editEsamiDaFare.setText(preferences.getString("esamidafare", ""));
        editMedia.setText(preferences.getString("media", ""));
        editCell1.setText(preferences.getString("cellulare1", ""));
        editCell2.setText(preferences.getString("cellulare2", ""));
        editCell3.setText(preferences.getString("cellulare3", ""));
        maschio.setChecked(preferences.getBoolean("maschio",false));
        femmina.setChecked(preferences.getBoolean("femmina",false));
        rating.setRating(preferences.getFloat("rating",0));*/

        //creazione istanza db
        mDbHelper = new StudenteDbHelper(this);



    }//end onCreate------------------_______-------------------_____________-------------------

    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };
    // updates the date in the TextView
    private void updateDisplay() {
       editDataNasc.setText(
                new StringBuilder()
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    //-----------------------------------------------------------------------------------

    public View.OnClickListener abilitaconferma = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (modifica.isChecked()) {

                editNome.setFocusableInTouchMode(true);
                editCognome.setFocusableInTouchMode(true);
                editDataNasc.setFocusableInTouchMode(true);
                editSito.setFocusableInTouchMode(true);
                editEsamiok.setFocusableInTouchMode(true);
                editEsamiDaFare.setFocusableInTouchMode(true);
                editMedia.setFocusableInTouchMode(true);
                editCell1.setFocusableInTouchMode(true);
                editCell2.setFocusableInTouchMode(true);
                editCell3.setFocusableInTouchMode(true);
                maschio.setClickable(true);
                femmina.setClickable(true);
                rating.setClickable(true);
            } else {

              /*  SharedPreferences preferences = getSharedPreferences(SCHEDA_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nome", editNome.getText().toString());
                editor.putString("cognome", editCognome.getText().toString());
                editor.putString("datanascita", editDataNasc.getText().toString());
                editor.putString("sitoweb", editSito.getText().toString());
                editor.putString("esamisvolti", editEsamiok.getText().toString());
                editor.putString("esamidafare", editEsamiDaFare.getText().toString());
                editor.putString("media", editMedia.getText().toString());
                editor.putString("cellulare1", editCell1.getText().toString());
                editor.putString("cellulare2", editCell2.getText().toString());
                editor.putString("cellulare3", editCell3.getText().toString());
                editor.putBoolean("maschio",maschio.isChecked());
                editor.putBoolean("femmina",femmina.isChecked());
                editor.putFloat("rating",rating.getRating());

                editor.apply();*/

              //salvare i dati inseriti nel db

                editNome.setFocusable(false);
                editCognome.setFocusable(false);
                editDataNasc.setFocusable(false);
                editSito.setFocusable(false);
                editEsamiok.setFocusable(false);
                editEsamiDaFare.setFocusable(false);
                editMedia.setFocusable(false);
                editCell1.setFocusable(false);
                editCell2.setFocusable(false);
                editCell3.setFocusable(false);
                maschio.setClickable(false);
                femmina.setClickable(false);
                rating.setClickable(false);
            }
        }
    };

}
