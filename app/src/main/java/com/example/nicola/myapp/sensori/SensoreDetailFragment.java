package com.example.nicola.myapp.sensori;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicola.myapp.R;
import com.example.nicola.myapp.dummy.SensoriContent;

import java.util.List;

/**
 * A fragment representing a single Sensore detail screen.
 * This fragment is either contained in a {@link SensoreListActivity}
 * in two-pane mode (on tablets) or a {@link SensoreDetailActivity}
 * on handsets.
 */
public class SensoreDetailFragment extends Fragment {
    //interface OnPassData{
      //  List<Sensor> getSensors();
    //}

    //private OnPassData onPassData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      //  try {
       //     onPassData = (SensoreDetailFragment.OnPassData) context;
       // }catch (ClassCastException e){
       //     throw new ClassCastException(context.toString());

       // }
    }

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private SensoriContent.DummySensore mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensoreDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = SensoriContent.SENSORI_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sensore_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.sensore_detail)).setText(mItem.details);
        }

        return rootView;
    }
}
