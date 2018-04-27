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

public class RedActivity extends AppCompatActivity {
    TextView showCount;
    private static int contatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_activity);

        //gestione del parametro ricevuto(getIntent) e associazione del suo valore alla chiave blue_counter
        Intent in = getIntent();
        int blueCounter = in.getIntExtra("blue_counter", 0);
        contatore = blueCounter + 1;

        Button btn_share = (Button) findViewById(R.id.btnShare);
        btn_share.setOnClickListener(doShare);

        Button btn_toBlue = (Button) findViewById(R.id.btnToBlue);
        btn_toBlue.setOnClickListener(goBlue);

        showCount = (TextView) findViewById(R.id.txtRed);
        showCount.setText(String.valueOf(contatore));

    }

    //intent esplicito per chiamata a blue con passaggio del parametro
    public View.OnClickListener goBlue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent vaiBlueActivity = getIntent();
            vaiBlueActivity.putExtra("red_counter",contatore);
            setResult(RESULT_OK,vaiBlueActivity);
            finish();
        }
    };
    //intent implicito ad ACTION_SEND
    public View.OnClickListener doShare = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String messaggio = getResources().getQuantityString(R.plurals.numberOfSteps, contatore, contatore);
            Intent condividiMsg = new Intent(Intent.ACTION_SEND);
            condividiMsg.setType("text/plain");
            condividiMsg.putExtra(Intent.EXTRA_TEXT, messaggio);
            startActivity(condividiMsg);

        }

    };

}