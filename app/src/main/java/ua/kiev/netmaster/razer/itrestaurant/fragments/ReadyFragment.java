package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.ProccessingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadyFragment extends Fragment implements View.OnClickListener {

    View root;

    public ReadyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            root = inflater.inflate(R.layout.fragment_ready, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        root.setOnClickListener(this);
        //closeFragment.run();
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
