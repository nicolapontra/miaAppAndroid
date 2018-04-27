package com.example.nicola.myapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewMap extends AppCompatActivity {
    EditText indirizzoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //R=class for manage Resources, Layout=nome cartella da cui cercare il file xml
        setContentView(R.layout.activity_view_map);

        indirizzoEditText = (EditText) findViewById(R.id.addressEdit);

        Button welcomeButton = (Button) findViewById(R.id.btnToMap);
        welcomeButton.setOnClickListener(vaiMappa);

        Button impostaDIBButton = (Button) findViewById(R.id.btnDIB);
        impostaDIBButton.setOnClickListener(impostaDIBButtonListener);
        //indirizzoEditText = (EditText) findViewById(R.id.addressEdit);
    }

    public View.OnClickListener vaiMappa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String indirizzo = indirizzoEditText.getText().toString();

            indirizzo = indirizzo.replace(' ', '+');
            Intent mappaIntent = new Intent(
                    //parse: rimanda al SO la scelta più idonea per l'operazione da compiere
                    Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + indirizzo)
            );
            startActivity(mappaIntent);
        }
    };

    public View.OnClickListener impostaDIBButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            indirizzoEditText.setText("Via Edoardo Orabona, 4, Bari");
        }
    };
}

