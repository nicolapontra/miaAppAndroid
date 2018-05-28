package com.example.nicola.myapp.fragment_static_layout;

import android.app.Activity;
import android.os.Bundle;

import com.example.nicola.myapp.R;
import com.example.nicola.myapp.fragment_static_layout.EsamiFrag.ListSelectionListener;
/**
 * Created by Nicola on 26/04/2018.
 */

public class DettaglioViewerActivity extends Activity implements ListSelectionListener {
 public static String[] listExams;
    public static String[] myExamsDetails;
    private DettaglioFrag details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listExams = getResources().getStringArray(R.array.Esami);
       myExamsDetails = getResources().getStringArray(R.array.Dettaglio);

        setContentView(R.layout.activity_dettaglio_view);

       details = (DettaglioFrag) getFragmentManager().findFragmentById(R.id.dettagli);
    }

    @Override
    public void onListSelection(int index) {
        if (details.getShownIndex() != index) {
          details.showQuoteAtIndex(index);
        }
    }

}
