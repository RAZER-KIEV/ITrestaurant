package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.communicator.IRestaurantService;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;
import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 26-Apr-16.
 */
public class TableDetailsFragment extends Fragment implements View.OnClickListener {

    private IRestaurantService iRestaurantService;
    private View root;
    private static final String pos = "pos";
    private Table curTable;
    private Button buttonA,buttonB,buttonC,buttonD,buttonCenter;
    private List<Request> thisTableRequests;
    private MyApplication myApplication;
    private ImageView aBell, bBell, cBell, dBell;

    public static TableDetailsFragment newInstance(int tablepos) {
        Bundle args = new Bundle();
        args.putInt(pos, tablepos);
        TableDetailsFragment fragment = new TableDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.table_details_fragment,container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iRestaurantService = (IRestaurantService) getActivity().getApplication();
        myApplication = (MyApplication) getActivity().getApplication();
        curTable = iRestaurantService.getDummyTableList().get(getArguments().getInt(pos));
        initButtons();
        initImageViews();
        makeLocalRequestList();
        enableBells();
    }

    private void initButtons() {
        int buttonSize = myApplication.getDisplayMetrics().widthPixels/4;
        buttonA = (Button) root.findViewById(R.id.buttonA);
            buttonA.setText(curTable.getNumber() + "A");
            buttonA.setHeight(buttonSize);
            buttonA.setWidth(buttonSize);
            buttonA.setOnClickListener(this);
        buttonB = (Button) root.findViewById(R.id.buttonB);
            buttonB.setText(curTable.getNumber() + "B");
            buttonB.setHeight(buttonSize);
            buttonB.setWidth(buttonSize);
            buttonB.setOnClickListener(this);
        buttonC = (Button) root.findViewById(R.id.buttonC);
            buttonC.setText(curTable.getNumber() + "C");
            buttonC.setHeight(buttonSize);
            buttonC.setWidth(buttonSize);
            buttonC.setOnClickListener(this);
        buttonD = (Button) root.findViewById(R.id.buttonD);
            buttonD.setText(curTable.getNumber() + "D");
            buttonD.setHeight(buttonSize);
            buttonD.setWidth(buttonSize);
            buttonD.setOnClickListener(this);
        buttonCenter = (Button) root.findViewById(R.id.buttonCenter);
            buttonCenter.setText(curTable.getNumber().toString());
            buttonCenter.setHeight(buttonSize / 2);
            buttonCenter.setWidth(buttonSize / 2);
            buttonCenter.setOnClickListener(this);
    }

    private void initImageViews(){
        aBell = (ImageView) root.findViewById(R.id.aBell);
        bBell = (ImageView) root.findViewById(R.id.bBell);
        cBell = (ImageView) root.findViewById(R.id.cBell);
        dBell = (ImageView) root.findViewById(R.id.dBell);
    }

    private void makeLocalRequestList(){
        thisTableRequests = new ArrayList<>();
        for(Request request : iRestaurantService.getRequestList()){
            if (request.getTable().getNumber()==curTable.getNumber()) thisTableRequests.add(request);
        }
    }

    private void enableBells(){
        for(Request request : thisTableRequests){
            switch (request.getSeat()){
                case A:
                    aBell.setVisibility(View.VISIBLE);
                    buttonA.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_white));
                    buttonA.setTextColor(Color.BLACK);
                    break;
                case B:
                    bBell.setVisibility(View.VISIBLE);
                    buttonB.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_white));
                    buttonB.setTextColor(Color.BLACK);
                    break;
                case C:
                    cBell.setVisibility(View.VISIBLE);
                    buttonC.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_white));
                    buttonC.setTextColor(Color.BLACK);
                    break;
                case D:
                    dBell.setVisibility(View.VISIBLE);
                    buttonD.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_white));
                    buttonD.setTextColor(Color.BLACK);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonA:
                myApplication.setCurrRequest(getFirstRequest(Seat.A));
                break;
            case R.id.buttonB:
                myApplication.setCurrRequest(getFirstRequest(Seat.B));
                break;
            case R.id.buttonC:
                myApplication.setCurrRequest(getFirstRequest(Seat.C));
                break;
            case R.id.buttonD:
                myApplication.setCurrRequest(getFirstRequest(Seat.D));
                break;
            case R.id.buttonCenter:
                // TODO: 26-Apr-16 make table admin
                L.t("Not Implemented yet!", getActivity());
                return;
        }if(myApplication.getCurrRequest()!=null)myApplication.commitFragment(new PlaceDetailsFragment(), getFragmentManager());
        else L.t("There aren't any requests!",getActivity());
    }

    private Request getFirstRequest(Seat seat){
        for(Request request  : thisTableRequests){
            if(request.getSeat()==seat) return request;
        }
        return null;
    }

}
