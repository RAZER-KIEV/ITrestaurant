package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 10-Apr-16.
 */
public class DoneButtonFragment extends Fragment implements View.OnClickListener {

    private View root;
    private MyApplication myApplication;
    private ImageView readyImg;
    private TextView readyTV, readyPlaceTV;
    private Button doneBt;

    public DoneButtonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_ready,container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        init();
    }

    private void init() {
        readyTV = (TextView) root.findViewById(R.id.readyTV);
        if(myApplication.getCurrRequest().getRequestTypes().element()== RequestType.Kitchen){
            L.t("Do somthing!", getActivity()); // TODO: 14-Apr-16  
        }else {
        readyTV.setVisibility(View.GONE); }
        doneBt = (Button) root.findViewById(R.id.doneBt);
        doneBt.setVisibility(View.VISIBLE);
        doneBt.setOnClickListener(this);
        readyImg = (ImageView) root.findViewById(R.id.readyImg);
        readyPlaceTV = (TextView) root.findViewById(R.id.readyPlaceTV);
        readyPlaceTV.setText(""+myApplication.getCurrRequest().getTable().getNumber() + myApplication.getCurrRequest().getSeat());
        readyImg.setImageDrawable(myApplication.getInfoFrImg());
    }


    @Override
    public void onClick(View v) {
        myApplication.commitFragment(ReadyFragment.newInstance(true),getFragmentManager());
    }
}

