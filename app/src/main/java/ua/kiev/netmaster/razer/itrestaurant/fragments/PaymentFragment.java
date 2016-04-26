package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements View.OnClickListener {

    private Button creditCardBt, cashBt;
    private View root;
    private MyApplication myApplication;


    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       root = inflater.inflate(R.layout.fragment_payment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        init();
    }

    private void init(){
        creditCardBt = (Button) root.findViewById(R.id.creditCardBt);
        creditCardBt.setOnClickListener(this);

        cashBt = (Button) root.findViewById(R.id.cashBt);
        cashBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.creditCardBt :
                myApplication.commitFragment(LikeACalcFragment.newInstance(false), getFragmentManager());
                break;
            case  R.id.cashBt:
                myApplication.commitFragment(LikeACalcFragment.newInstance(true), getFragmentManager());
                break;
        }
    }
}
