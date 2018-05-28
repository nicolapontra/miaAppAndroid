package com.example.nicola.myapp.fragment_static_layout;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nicola.myapp.R;

/**
 * Created by Nicola on 26/04/2018.
 */

public class EsamiFrag extends ListFragment {
    private ListSelectionListener list = null;

    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int posizione, long id) {
        getListView().setItemChecked(posizione, true);
        list.onListSelection(posizione);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            list = (ListSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.activity_esami_frag, DettaglioViewerActivity.listExams));
    }

}
