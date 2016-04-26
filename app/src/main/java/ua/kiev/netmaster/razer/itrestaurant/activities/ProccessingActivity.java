package ua.kiev.netmaster.razer.itrestaurant.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.fragments.BroughtFragment;
import ua.kiev.netmaster.razer.itrestaurant.fragments.DoneButtonFragment;
import ua.kiev.netmaster.razer.itrestaurant.fragments.LikeACalcFragment;
import ua.kiev.netmaster.razer.itrestaurant.fragments.OrderProc;
import ua.kiev.netmaster.razer.itrestaurant.fragments.TableDetailsFragment;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

public class ProccessingActivity extends AppCompatActivity implements LikeACalcFragment.CalcCommunicator {

    private Request request;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.l("onCreate()", this);
        setContentView(R.layout.activity_proccessing);
        //getIntent().getIntExtra("req_possition", -1);
        myApplication = (MyApplication) getApplication();
        request = myApplication.getCurrRequest();
        if(request!=null)L.l("request.toString()  "+ request.toString(), this);
        //generateMenu();

        //choseFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.l("onResume", this);
        choseFragment();
    }

    private void choseFragment() {
        L.l("choseFragment", this);
        int pos=getIntent().getIntExtra("pos",-1);
        //getIntent().putExtra("pos", -1);
        if(pos>=0) myApplication.commitFragment(TableDetailsFragment.newInstance(pos),getSupportFragmentManager());
        else if (request.getRequestTypes().element() == RequestType.ComeToMe) {
            myApplication.commitFragment(new OrderProc(), getSupportFragmentManager());
        } else if (request.getRequestTypes().element() == RequestType.Taxi) {
            myApplication.setInfoFrImg(ContextCompat.getDrawable(this, R.drawable.ic_taxi_icon));
            myApplication.commitFragment(new DoneButtonFragment(), getSupportFragmentManager());
        } else if (request.getRequestTypes().element() == RequestType.GetCash) {
            myApplication.commitFragment(LikeACalcFragment.newInstance(true), getSupportFragmentManager());
        } else if (request.getRequestTypes().element() == RequestType.Kitchen) {
            myApplication.commitFragment(new BroughtFragment(), getSupportFragmentManager());
        } else if (request.getRequestTypes().element() == RequestType.CreditCard){
            myApplication.commitFragment(LikeACalcFragment.newInstance(false), getSupportFragmentManager());
        } else if (request.getRequestTypes().element()== RequestType.Cash){
            myApplication.setInfoFrImg(ContextCompat.getDrawable(this, R.drawable.ic_receipt_schedule_currency));
            myApplication.commitFragment(new DoneButtonFragment(),getSupportFragmentManager());
        } else if (request.getRequestTypes().element() ==RequestType.Cutlery){
            myApplication.setInfoFrImg(ContextCompat.getDrawable(this, R.drawable.ic_fork_spoon));
            myApplication.commitFragment(new DoneButtonFragment(), getSupportFragmentManager());
        } else getSupportFragmentManager().popBackStack();
    }

    @Override
    public void myCalcClikMethod(View v) {
        myApplication.getLikeACalcFragment().myClickCalc(v);
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()<2) finish();
        L.l("onBackPressed; getSupportFragmentManager().getBackStackEntryCount()= ",this);
        super.onBackPressed();
        L.l("onBackPressed; getSupportFragmentManager().getBackStackEntryCount()= " + getSupportFragmentManager().getBackStackEntryCount(), this);
        //getSupportFragmentManager().getBackStackEntryCount();
    }
}
