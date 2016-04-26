package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment1 extends Fragment implements View.OnClickListener {

    private View root;
    private MyApplication myApplication;
    private ImageView readyImg;
    private TextView readyTV, readyPlaceTV;
    private String message, place;
    private boolean changeMode;
   // private La
    //private Drawable img;


    public InfoFragment1() {
        // Required empty public constructor
    }

    public static InfoFragment1 newInstance(String message, String place, boolean changeMode) {
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("place", place);
        args.putBoolean("changeMode", changeMode);
        InfoFragment1 fragment = new InfoFragment1();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_ready, container, false);
        root.setOnClickListener(this);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        message = getArguments().getString("message");
        place = getArguments().getString("place");
        changeMode = getArguments().getBoolean("changeMode");
        init();
    }

    private void init() {
        readyImg = (ImageView) root.findViewById(R.id.readyImg);
        readyImg.setImageDrawable(myApplication.getInfoFrImg());
        readyTV  = (TextView) root.findViewById(R.id.readyTV);
            readyTV.setText(message);
        readyPlaceTV = (TextView) root.findViewById(R.id.readyPlaceTV);
            readyPlaceTV.setText(place);
    }

    @Override
    public void onClick(View v) {
        if(changeMode){
            myApplication.commitFragment(new DoneButtonFragment(), getFragmentManager());
            //L.t("todo myApplication.commitFragment(ExecuteBtnFragment);", getActivity());
        }else
            myApplication.commitFragment(ReadyFragment.newInstance(true),getFragmentManager());
    }
}
