package com.example.nicola.myapp.fragment_static_layout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicola.myapp.R;
import com.example.nicola.myapp.fragment_static_layout.DettaglioViewerActivity;

/**
 * Created by Nicola on 26/04/2018.
 */

public class DettaglioFrag extends Fragment{

    private TextView vistaDettagli = null;
    private int cursore = -1;
    private int dettArraylungh;

    private static final String TAG = "DetailFragment";

    public int getShownIndex() {
        return cursore;
    }

    public void showQuoteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= dettArraylungh)
            return;
        cursore = newIndex;
        vistaDettagli.setText(DettaglioViewerActivity.myExamsDetails[cursore]);
    }



    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dettaglio_frag, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vistaDettagli = (TextView) getActivity().findViewById(R.id.dettagliView);
        dettArraylungh = DettaglioViewerActivity.myExamsDetails.length;
    }



}
