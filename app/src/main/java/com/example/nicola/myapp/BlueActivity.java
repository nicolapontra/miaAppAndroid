package com.example.nicola.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nicola on 05/04/2018.
 */

public class BlueActivity extends AppCompatActivity {

    //private static final int RED_REQUEST = 2;
    private static int contatore = 1;
    TextView showCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);


        Button btn_Home = (Button) findViewById(R.id.btnHome);
        btn_Home.setOnClickListener(goHome);

        Button btn_toRed = (Button) findViewById(R.id.btntoRed);
        btn_toRed.setOnClickListener(goRed);

        showCount = (TextView) findViewById(R.id.txtBlue);
        showCount.setText(String.valueOf(contatore));


    }

    public View.OnClickListener goRed = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //intent esplicito per chiamata a red
            Intent vaiRedActivity = new Intent(getApplicationContext(), RedActivity.class);

            //passaggio di parametro primitivo (non oggetto)
            vaiRedActivity.putExtra("blue_counter",contatore);

            //implica l'utilizzo di onActivityResult
            startActivityForResult(vaiRedActivity,1);
        }
    };

    public View.OnClickListener goHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent restituisciAHome = getIntent();
            restituisciAHome.putExtra("blue_counter",contatore);
            setResult(RESULT_OK,restituisciAHome);
            finish();
        }
    };

    //poichè BlueActivity passa un parametro e ne aspetta una risposta da Red, possiamo usare onActRes
    //in questo caso dal parametro atteso da RedAct e lo usiamo per l'incremento
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){

            //lettura del parametro ricevuto tramite getIntExtra poichè riceviamo un intero(ci sono molteplici get)
                contatore = data.getIntExtra("red_counter", 0);
                ++contatore;
                showCount.setText(String.valueOf(contatore));
            }
    }

}
