package com.example.nicola.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    TextView textToShow;
    private static final String TAG = "LauncherActivity";

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        textToShow = (TextView) findViewById(R.id.TextView_text);
        String myname = getString(R.string.app_name);
        textToShow.setText(getString(R.string.button_welcome, myname));

        Button btnGoMap = (Button) findViewById(R.id.buttonMap);
        btnGoMap.setOnClickListener(goMap);

        Button btnGoTorre = (Button) findViewById(R.id.btnTorreHanoi);
        btnGoTorre.setOnClickListener(goTorre);

        Button btnGoPingPong = (Button) findViewById(R.id.btnPingPong);
        btnGoPingPong.setOnClickListener(goPingPong);

        Button btnGoEsami = (Button) findViewById(R.id.btnEsamiListActivity);
        btnGoEsami.setOnClickListener(goEsami);

        Button btnGoEsamiFrag = (Button) findViewById(R.id.btnEsamiFrag);
        btnGoEsamiFrag.setOnClickListener(goEsamiFrag);
    }
    public View.OnClickListener goEsami = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent vaiAEsami = new Intent(LauncherActivity.this, EsamiActivity.class);
            startActivity(vaiAEsami);
        }
    };

    public View.OnClickListener goEsamiFrag = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent vaiAEsamifrag = new Intent(LauncherActivity.this, DettaglioViewerActivity.class);
            startActivity(vaiAEsamifrag);
        }
    };

    public View.OnClickListener welcomeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textToShow.setText(R.string.button_welcome);
        }
    };

    public View.OnClickListener goodbyeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textToShow.setText(R.string.button_goodbye);
        }
    };

    //metodo per passare all'activity ViewMap
    public View.OnClickListener goMap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //INTENT: è una delle modalità per comunicare tra activity
            Intent vaiAMappa = new Intent(LauncherActivity.this, ViewMap.class);

            startActivity(vaiAMappa);
        }
    };

    public View.OnClickListener goTorre = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent vaiATorrehanoi = new Intent(LauncherActivity.this, TorreHanoiActivity.class);
            startActivity(vaiATorrehanoi);
        }
    };

    //intent Esplicito
    public View.OnClickListener goPingPong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent vaiBlueActivity = new Intent(LauncherActivity.this, BlueActivity.class);
            vaiBlueActivity.setType("text/plain");
            startActivityForResult(vaiBlueActivity,1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK ) {

            //gestione plurals
            Resources res = getResources();
            int contatore = data.getIntExtra("blue_counter",1);
            String passi = res.getQuantityString(R.plurals.numberOfSteps, contatore, contatore);

            //gestione risposta ricevuta
            Intent sendIntent = new Intent(Intent.ACTION_SEND);  //intent implicito
            sendIntent.setType("text/plain");     //setto il tipo di intent
            sendIntent.putExtra(Intent.EXTRA_TEXT, passi);  //pongo il contenuto
            startActivity(sendIntent);
        }
    }


    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }




}//end class