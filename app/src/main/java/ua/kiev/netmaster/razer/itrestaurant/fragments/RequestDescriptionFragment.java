package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.activities.ProccessingActivity;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class RequestDescriptionFragment extends Fragment implements View.OnClickListener {

    private MyApplication myApplication;
    private Button accept;
    private TextView whatToDo;
    private View rootV;
    private Request current;
    private static SecondPageFragmentListener secondPageFragmentListener;

    public static RequestDescriptionFragment newInstance(SecondPageFragmentListener listener) {
        secondPageFragmentListener = listener;
        return new RequestDescriptionFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.l("onCreateView()", this);
        rootV = inflater.inflate(R.layout.request_description_fragment_lay, container, false);
        return rootV;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        L.l("onActivityCreated()", this);
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        init();
        createMessage();
    }

    private void createMessage() {
        L.l("createMessage()", this);
        String message="";
        L.l("current.getRequestTypes().size() = "+current.getRequestTypes().size());
        switch (current.getRequestTypes().element()){
            case ComeToMe:
                message+="Иди на место "+current.getTable().getNumber()+current.getSeat();
                break;
            case CreditCard:
                message+="Бери терминал!\nна место "+current.getTable().getNumber()+current.getSeat();
                break;
            case Cutlery:
                message= "Принести приборы \nза место "+current.getTable().getNumber()+current.getSeat();
                break;
            case Taxi:
                message = "Звони в такси \n"+current.getTable().getNumber()+current.getSeat();
                break;
            case Cash:
                message="Бегом за чеком! \n"+current.getTable().getNumber()+current.getSeat();
                break;
            case GetCash:
                message="Взял деньги? \n" +current.getTable().getNumber()+current.getSeat();
                break;
            case Kitchen:
                message = "Вали на кухню за едой \n" +current.getTable().getNumber()+current.getSeat();
                break;
        }
        myApplication.setCurrMessage(message);
        L.l("message = " +message, this);
        whatToDo.setText(message);
    }

    private void init() {
        L.l("init()", this);
        accept = (Button) rootV.findViewById(R.id.acceptBT);
        accept.setOnClickListener(this);
        whatToDo = (TextView) rootV.findViewById(R.id.whatToDoTV);
        current = myApplication.getCurrRequest();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ProccessingActivity.class);
        startActivity(intent);
    }


    public static SecondPageFragmentListener getSecondPageFragmentListener() {
        return secondPageFragmentListener;
    }
}
