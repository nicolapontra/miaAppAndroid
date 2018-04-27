package com.example.nicola.myapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.LinkedList;


/**
 * Created by Nicola on 22/03/2018.
 */

public class TorreHanoiActivity extends AppCompatActivity {

    TextView textTorreUno;
    TextView textTorreDue;
    TextView textTorreTre;

    TextView textPezziUno;
    TextView textPezziDue;
    TextView textPezziTre;

    LinkedList<Integer> torreUno = new LinkedList<Integer>();
    LinkedList<Integer> torreDue = new LinkedList<Integer>();
    LinkedList<Integer> torreTre = new LinkedList<Integer>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torre);

        //Start game inizialization
        for (int n = 5; n > 0; n--) {
            torreUno.add(n);
        }

        textTorreUno = (TextView) findViewById(R.id.textTorreUno);
        textTorreDue = (TextView) findViewById(R.id.textTorreDue);
        textTorreTre = (TextView) findViewById(R.id.textTorreTre);

        stampaTorre(torreUno, textTorreUno);

        //PLURALS
        int count= torreUno.size();
        Resources res = getResources();
        String numpezzi;
        numpezzi = res.getQuantityString(R.plurals.numberOfDiscs, count,count);
        textPezziUno = (TextView) findViewById(R.id.txtPezziUno);
        textPezziUno.setText(numpezzi);

        int count2= torreDue.size();
        Resources res2 = getResources();
        String numpezziDue;
        numpezziDue = res.getQuantityString(R.plurals.numberOfDiscs, count2,count2);
        textPezziDue = (TextView) findViewById(R.id.txtPezziDue);
        textPezziDue.setText(numpezziDue);

        int count3= torreTre.size();
        Resources res3 = getResources();
        String numpezziTre;
        numpezziTre = res.getQuantityString(R.plurals.numberOfDiscs, count3,count3);
        textPezziTre = (TextView) findViewById(R.id.txtPezziTre);
        textPezziTre.setText(numpezziTre);

        Button btnGoSecond = (Button) findViewById(R.id.btnToSecond);
        btnGoSecond.setText(">");
        btnGoSecond.setOnClickListener(spostaDisco1);

        Button btnGoFirst = (Button) findViewById(R.id.btnToFirst);
        btnGoFirst.setText("<");
        btnGoFirst.setOnClickListener(spostaDisco2);

        Button btnGoThird = (Button) findViewById(R.id.btnToThird);
        btnGoThird.setText(">");
        btnGoThird.setOnClickListener(spostaDisco3);

        Button btnReturnSecond = (Button) findViewById(R.id.btnReturnSecond);
        btnReturnSecond.setText("<");
        btnReturnSecond.setOnClickListener(spostaDisco4);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(ritornaLauncher);
    };

    //metodi per lo scambio dei dischi
    public View.OnClickListener spostaDisco1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (torreUno.isEmpty() ) {

            }else if (!torreDue.isEmpty() && torreUno.getLast()>torreDue.getLast() ) {
            }else{
                torreDue.add(torreUno.removeLast());
            }

            //ridisegna torre1
            stampaTorre(torreUno,textTorreUno);

            //ridisegna torre2
           stampaTorre(torreDue,textTorreDue);

            int count= torreUno.size();
            Resources res = getResources();
            String numpezzi;
            numpezzi = res.getQuantityString(R.plurals.numberOfDiscs, count,count);
            textPezziUno = (TextView) findViewById(R.id.txtPezziUno);
            textPezziUno.setText(numpezzi);

            int count2= torreDue.size();
            Resources res2 = getResources();
            String numpezziDue;
            numpezziDue = res.getQuantityString(R.plurals.numberOfDiscs, count2,count2);
            textPezziDue = (TextView) findViewById(R.id.txtPezziDue);
            textPezziDue.setText(numpezziDue);

        }
    };

    public View.OnClickListener spostaDisco2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (torreDue.isEmpty() ) {

            }
            else if(!torreUno.isEmpty() && torreDue.getLast()>torreUno.getLast()) {

            }  else{
                torreUno.add(torreDue.removeLast());
            }

            //ridisegna torre1
            stampaTorre(torreUno,textTorreUno);

            //ridisegna torre2
            stampaTorre(torreDue,textTorreDue);

            int count= torreUno.size();
            Resources res = getResources();
            String numpezzi;
            numpezzi = res.getQuantityString(R.plurals.numberOfDiscs, count,count);
            textPezziUno = (TextView) findViewById(R.id.txtPezziUno);
            textPezziUno.setText(numpezzi);

            int count2= torreDue.size();
            Resources res2 = getResources();
            String numpezziDue;
            numpezziDue = res.getQuantityString(R.plurals.numberOfDiscs, count2,count2);
            textPezziDue = (TextView) findViewById(R.id.txtPezziDue);
            textPezziDue.setText(numpezziDue);

        }
    };



    public View.OnClickListener spostaDisco3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (torreDue.isEmpty()) {

            }else if (!torreTre.isEmpty() && torreDue.getLast()>torreTre.getLast()) {

            } else {
                torreTre.add(torreDue.removeLast());
            }

            //ridisegna torre2
            stampaTorre(torreDue,textTorreDue);

            //ridisegna torre3
            stampaTorre(torreTre,textTorreTre);

            int count= torreDue.size();
            Resources res = getResources();
            String numpezzi;
            numpezzi = res.getQuantityString(R.plurals.numberOfDiscs, count,count);
            textPezziDue = (TextView) findViewById(R.id.txtPezziDue);
            textPezziDue.setText(numpezzi);

            int count2= torreTre.size();
            Resources res2 = getResources();
            String numpezziTre;
            numpezziTre = res.getQuantityString(R.plurals.numberOfDiscs, count2,count2);
            textPezziTre = (TextView) findViewById(R.id.txtPezziTre);
            textPezziTre.setText(numpezziTre);

        }
    };

    public View.OnClickListener spostaDisco4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (torreTre.isEmpty() ) {

            }else if (!torreDue.isEmpty() && torreTre.getLast()>torreDue.getLast() ) {

            }else{
                        torreDue.add(torreTre.removeLast());
                        }

            //ridisegna torre3
            stampaTorre(torreTre,textTorreTre);

            //ridisegna torre2
            stampaTorre(torreDue,textTorreDue);

            int count= torreTre.size();
            Resources res = getResources();
            String numpezzi;
            numpezzi = res.getQuantityString(R.plurals.numberOfDiscs, count,count);
            textPezziTre = (TextView) findViewById(R.id.txtPezziTre);
            textPezziTre.setText(numpezzi);

            int count2= torreDue.size();
            Resources res2 = getResources();
            String numpezziDue;
            numpezziDue = res.getQuantityString(R.plurals.numberOfDiscs, count2,count2);
            textPezziDue = (TextView) findViewById(R.id.txtPezziDue);
            textPezziDue.setText(numpezziDue);

        }
    };



    private void stampaTorre (LinkedList<Integer> t,TextView tv){
        StringBuilder torre = new StringBuilder();
        for (Integer elem : t){
            for (int i=0; i<elem; i++){
                torre.append("[]");
            }
            torre.append("\n");
        }
        tv.setText(torre.toString());
    };
/*
    private void pluralsSet (LinkedList<Integer> t1,LinkedList<Integer> t2, TextView tv1, TextView tv2 ,int id1,R.id id2 ){
        int count= t1.size();
        Resources res = getResources();
        String numpezzi;
        numpezzi = res.getQuantityString(R.plurals.numberOfDiscs, count,count);
        tv1 = (TextView) findViewById(R.id.id1);
        tv1.setText(numpezzi);

        int count2= t2.size();
        Resources res2 = getResources();
        String numpezziDue;
        numpezziDue = res.getQuantityString(R.plurals.numberOfDiscs, count2,count2);
        tv2 = (TextView) findViewById(id2);
        tv2.setText(numpezziDue);
    }
*/
    private View.OnClickListener ritornaLauncher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
