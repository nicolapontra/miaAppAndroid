package com.example.nicola.myapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SchedaStudenteActivity extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;
    EditText edit8;
    EditText edit9;
    EditText edit10;
    RadioButton maschio;
    RadioButton femmina;
    RatingBar rating;
    ToggleButton modifica;

    //------------------------------------------------------------------------
    Button changeData;
    static final int DATE_DIALOG_ID = 0;
    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_studente);

        edit1 = (EditText) findViewById(R.id.editText1);
        edit1.setFocusable(false);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit2.setFocusable(false);
        edit3 = (EditText) findViewById(R.id.editText3);
        edit3.setFocusable(false);
        edit4 = (EditText) findViewById(R.id.editText4);
        edit4.setFocusable(false);
        edit5 = (EditText) findViewById(R.id.editText5);
        edit5.setFocusable(false);
        edit6 = (EditText) findViewById(R.id.editText6);
        edit6.setFocusable(false);
        edit7 = (EditText) findViewById(R.id.editText7);
        edit7.setFocusable(false);
        edit8 = (EditText) findViewById(R.id.editText8);
        edit8.setFocusable(false);
        edit9 = (EditText) findViewById(R.id.editText9);
        edit9.setFocusable(false);
        edit10 = (EditText) findViewById(R.id.editText10);
        edit10.setFocusable(false);
        maschio = (RadioButton) findViewById(R.id.radiobtn1);
        maschio.setClickable(false);
        femmina = (RadioButton) findViewById(R.id.radiobtn2);
        femmina.setClickable(false);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        rating.setClickable(false);

        modifica = (ToggleButton) findViewById(R.id.toggleButton);
        modifica.setOnClickListener(abilitaconferma);
//----------------------------------------------------------------------------------------
        changeData = (Button) findViewById(R.id.btnChangeData);
        // add a click listener to the button
        changeData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

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
       edit3.setText(
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
                edit1.setFocusableInTouchMode(true);
                edit2.setFocusableInTouchMode(true);
                edit3.setFocusableInTouchMode(true);
                edit4.setFocusableInTouchMode(true);
                edit5.setFocusableInTouchMode(true);
                edit6.setFocusableInTouchMode(true);
                edit7.setFocusableInTouchMode(true);
                edit8.setFocusableInTouchMode(true);
                edit9.setFocusableInTouchMode(true);
                edit10.setFocusableInTouchMode(true);
                maschio.setClickable(true);
                femmina.setClickable(true);
                rating.setClickable(true);
            } else {
                edit1.setFocusable(false);
                edit2.setFocusable(false);
                edit3.setFocusable(false);
                edit4.setFocusable(false);
                edit5.setFocusable(false);
                edit6.setFocusable(false);
                edit7.setFocusable(false);
                edit8.setFocusable(false);
                edit9.setFocusable(false);
                edit10.setFocusable(false);
                maschio.setClickable(false);
                femmina.setClickable(false);
                rating.setClickable(false);
            }
        }
    };

}
