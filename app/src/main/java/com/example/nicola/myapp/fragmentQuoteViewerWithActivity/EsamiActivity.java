package com.example.nicola.myapp.fragmentQuoteViewerWithActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nicola.myapp.R;

/**
 * Created by Nicola on 26/04/2018.
 * Questa classe è collegata con DettaglioActivity.java e il layout activity_esami(in seguito sostituito con activity_es_liarlay).
 * Estende la classe ListActivity
 */

public class EsamiActivity extends ListActivity {

    public static String[] myExams;
    public static String[] dettagliEsami;

    public static final String INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("onCreateEsami", getClass().getSimpleName() + ":entered onAttach()");
        setContentView(R.layout.activity_es_linearlay);

        myExams = getResources().getStringArray(R.array.Esami);
        dettagliEsami = getResources().getStringArray(R.array.Dettaglio);

        setListAdapter(new ArrayAdapter<String>(EsamiActivity.this,
                android.R.layout.simple_list_item_1, myExams));

    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        Intent showItemIntent = new Intent(EsamiActivity.this,
                DettaglioActivity.class);
        showItemIntent.putExtra(INDEX, dettagliEsami[pos]);
        startActivity(showItemIntent);
    }
}
