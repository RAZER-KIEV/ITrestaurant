package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeACalcFragment extends Fragment {

    private boolean modeCash;
    private MyApplication myApplication;
    private TextView monitor;
    private View root;
    private CalcCommunicator calcCommunicator;
    private LinearLayout cont;

    public LikeACalcFragment() {

    }


    public static LikeACalcFragment newInstance(boolean cash) {
        Bundle args = new Bundle();
        args.putBoolean("modeCash", cash);
        LikeACalcFragment fragment = new LikeACalcFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_like_acalc, container, false);
        cont = (LinearLayout) root.findViewById(R.id.linear_calc_cont);
        monitor = (TextView) root.findViewById(R.id.calcMonitorTv);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        modeCash = getArguments().getBoolean("modeCash");
        if(!modeCash){
            cont.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorGreen));
            monitor.setText(R.string.xxx);
            monitor.setTextSize(25f);
        }
        myApplication = (MyApplication) getActivity().getApplication();
        calcCommunicator = (CalcCommunicator) getActivity();
        myApplication.setLikeACalcFragment(this);
    }

    public void myClickCalc(View v ){
        //if ani digit
        if (v.getId() == R.id.bt1 || v.getId() == R.id.bt2 || v.getId() == R.id.bt3 || v.getId() == R.id.bt4 || v.getId() == R.id.bt5 || v.getId() == R.id.bt6 || v.getId() == R.id.bt7 || v.getId() == R.id.bt8 || v.getId() == R.id.bt9 || v.getId() == R.id.bt0) {
            monitor.setText(monitor.getText().toString() + ((Button) v).getText());
            return;
        } else if (v.getId() == R.id.btBack) {
            StringBuilder sb = new StringBuilder(monitor.getText());
            if(sb.length()>0)sb.setLength(sb.length() - 1);
            monitor.setText(sb.toString());
            return;
        } else if (v.getId() == R.id.btApply) {
            if(modeCash) {
                if(myApplication.getCurrRequest().getOrder()!=null) {
                    double monitord;
                    try {
                        monitord  = Double.parseDouble(monitor.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                        L.t("Enter the correct sum!", getActivity());
                        return;
                    }
                    double change = monitord - myApplication.getCurrRequest().getOrder().getTotal();
                    myApplication.setCurChange(change);
                    myApplication.setInfoFrImg(ContextCompat.getDrawable(getContext(), R.drawable.ic_change_icon));
                    myApplication.commitFragment(InfoFragment1.newInstance("Please give a change\n" + String.format(" $ %.2f", change), "" + myApplication.getCurrRequest().getTable().getNumber() + myApplication.getCurrRequest().getSeat(), true), getFragmentManager());
                }else L.t("No any orders!",getActivity());
            } else {
                myApplication.setInfoFrImg(ContextCompat.getDrawable(getContext(), R.drawable.ic_receipt_schedule_currency));
                myApplication.commitFragment(InfoFragment1.newInstance("Please give receipt to the visitor", "" + myApplication.getCurrRequest().getTable().getNumber() + myApplication.getCurrRequest().getSeat(), false), getFragmentManager());
            }
        }
    }

    public interface CalcCommunicator{
        void myCalcClikMethod(View v);
    }
}
