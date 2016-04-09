package ua.kiev.netmaster.razer.itrestaurant.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.fragments.OrderProc;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

public class ProccessingActivity extends AppCompatActivity {

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
        L.l("request.toString()  "+ request.toString(), this);
        generateMenu();
        choseFragment();
    }

    private void choseFragment(){
        if(request.getRequestTypes().get(0)== RequestType.ComeToMe){
            myApplication.commitFragment(new OrderProc(),getSupportFragmentManager());
        }
    }

    private void generateMenu(){
        if(myApplication.getMenuItemList()==null){
            List<MenuItem> menuItems = new ArrayList<>();
            int counter = 0;
            for(String name : dishesNames){
             MenuItem menuItem = new MenuItem();
                menuItem.setName(name);
                menuItem.setDescription("Very tasty " + name);
                menuItem.setPrice(dishesPrices[counter]);
                menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.salad_icon));
                menuItems.add(menuItem);
                counter++;
            }
            myApplication.setMenuItemList(menuItems);
        }
    }

    private String[] dishesNames = {"Sup", "Borsch", "Kasha", "Pizza", "Sushi", "Salat Greckiy", "Salat Letniy", "Black Tea", "Green Tea", "Mohito", "Limonad" };
    private Double[] dishesPrices = {25.50d, 30.00d, 20.45d, 82.45d, 55.56d, 67.40d, 30.10d, 21.50d, 21.50d, 53.70d, 25.00d};
}
