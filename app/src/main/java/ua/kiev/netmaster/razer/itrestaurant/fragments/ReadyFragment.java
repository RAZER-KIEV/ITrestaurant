package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadyFragment extends Fragment implements View.OnClickListener {


    private MyApplication myApplication;
    private View root;

    public static ReadyFragment newInstance(boolean finishActivity) {

        Bundle args = new Bundle();
        args.putBoolean("finishActivity", finishActivity);
        ReadyFragment fragment = new ReadyFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        myApplication = (MyApplication) getActivity().getApplication();
    }

    @Override
    public void onClick(View v) {
        if(getArguments().getBoolean("finishActivity",false)) {
            ArrayList<Request> reqL =  myApplication.getRequestList();
            int pos = reqL.indexOf(myApplication.getCurrRequest());
            myApplication.getCurrRequest().getRequestTypes().remove();
            reqL.remove(pos);
            if(myApplication.getCurrRequest().getRequestTypes().size()>0) reqL.add(pos,myApplication.getCurrRequest());
            myApplication.setRequestList(reqL);
            myApplication.setBackToRequesqList(true);
            getActivity().finish();
        }
        else getActivity().getSupportFragmentManager().popBackStack();
    }
}
