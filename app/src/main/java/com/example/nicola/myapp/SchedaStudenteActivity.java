package com.example.nicola.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SchedaStudenteActivity extends AppCompatActivity {

    EditText edit1;
    ToggleButton modifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_studente);

        edit1 = (EditText) findViewById(R.id.editText1);
        edit1.setFocusable(false);

        modifica = (ToggleButton) findViewById(R.id.toggleButton);
        modifica.setOnClickListener(abilitaconferma);

    }

    public View.OnClickListener abilitaconferma = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (modifica.isChecked()) {
                edit1.setFocusableInTouchMode(true);
            } else {
                edit1.setFocusable(false);
            }
        }
    };

}
