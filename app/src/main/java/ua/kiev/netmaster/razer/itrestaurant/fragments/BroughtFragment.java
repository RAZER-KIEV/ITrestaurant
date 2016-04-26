package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class BroughtFragment extends Fragment {

    private View root;
    private MyApplication myApplication;
    private TextView dishNameTV, brought_placeTV;
    private ImageView dish_iconIV;
    private Button broughtBT;
    private Request curRequest;

    public BroughtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.kitchen_dish_brought, container,false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        curRequest = myApplication.getCurrRequest();
        init();
    }

    private void init() {
        L.l("init()", this);
        brought_placeTV = (TextView) root.findViewById(R.id.brought_placeTV);
            brought_placeTV.setText(""+curRequest.getTable().getNumber()+curRequest.getSeat());
        dishNameTV = (TextView) root.findViewById(R.id.dishNameTV);
            dishNameTV.setText(curRequest.getOrder().getItems()[0].getName());
        dish_iconIV = (ImageView) root.findViewById(R.id.dish_iconIV);
        L.l("curRequest.getOrder().getItems()[0]"+ curRequest.getOrder().getItems()[0].getMenuItem(), this);
        L.l("curRequest.getOrder().getItems()[0].getMenuItem().getIcon() = "+curRequest.getOrder().getItems()[0].getMenuItem().getIcon(),this);
        dish_iconIV.setImageDrawable(curRequest.getOrder().getItems()[0].getMenuItem().getIcon());
        broughtBT = (Button) root.findViewById(R.id.broughtBT);
            broughtBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myApplication.commitFragment(ReadyFragment.newInstance(true),getFragmentManager());
                }
            });
    }
}
