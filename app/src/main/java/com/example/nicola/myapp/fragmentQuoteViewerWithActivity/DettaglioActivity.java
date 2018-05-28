package com.example.nicola.myapp.fragmentQuoteViewerWithActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.nicola.myapp.R;

/**
 * Created by Nicola on 26/04/2018.
 */

public class DettaglioActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_es_linearlay);

        Intent intent = getIntent();
        String stringadettaglio = intent.getStringExtra(EsamiActivity.INDEX);

        if (null != stringadettaglio) {
            setListAdapter(new ArrayAdapter<String>(DettaglioActivity.this,
                    android.R.layout.simple_list_item_1, new String[] { stringadettaglio }));
        }
    }
}
