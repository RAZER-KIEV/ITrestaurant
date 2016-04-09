package ua.kiev.netmaster.razer.itrestaurant.activities;

import android.app.Activity;
import android.app.Application;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Order;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;
import ua.kiev.netmaster.razer.itrestaurant.enums.TableStatus;
import ua.kiev.netmaster.razer.itrestaurant.fragments.SecondPageFragmentListener;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class MyApplication extends Application {


    private List<Order> orderList;
    private List<Request> requestList;
    private SimpleDateFormat simpleDateFormat;
    private List<Table> dummyTableList;
    private int number=0;
    private Request currRequest;
    private String currMessage;
    private List<MenuItem> menuItemList;
    private Order currOrder;
    private int curOderPosition;


    @Override
    public void onCreate() {
        L.l("onCreate", this);
        super.onCreate();
        simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
    }

    public void commitFragment(Fragment fragment, FragmentManager fragmentManager) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        String str[]=fragment.getClass().toString().split("\\.");
        String fragmentName = str[str.length-1].trim();
        L.l(" MYAPPLICATION. commitFragment. - " + fragmentName);
        //L.l("fragment.getClass().getCanonicalName() = " + fragment.getClass().getCanonicalName(),this);
        fragmentTransaction.replace(R.id.processing_container, fragment, fragmentName);
        fragmentTransaction.addToBackStack("BackTag");
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    public void setToolbarTitle(String title,Activity activity ){
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public List<Request> getRequestList() {
        if(requestList==null||requestList.size()<1){
            makeDummyRequestList(50);
        }
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    private void makeDummyTableList(int quantity){
        dummyTableList = new ArrayList<>();
        for(int i=0;i<quantity; i++){
            dummyTableList.add(makedummyTable());
        }
    }


    private Table makedummyTable(){
        Table table = new Table();
        table.setNumber((long) number++);
        table.setSeats(Seat.values());
        table.setStatus(TableStatus.SLEEPING);
        table.setWallpaper(ContextCompat.getDrawable(this, R.drawable.table_bcgrnd));
        return table;
    }

    public List<Table> getDummyTableList() {
        if(dummyTableList==null||dummyTableList.size()<1){
            makeDummyTableList(5);
        }
        return dummyTableList;
    }


    private void makeDummyRequestList(int quantity){
        requestList = new ArrayList<>();
        for(int i =0; i<quantity; i++){
            requestList.add(makeDummyRequest());
        }
    }

    private Request makeDummyRequest(){
        Request request = new Request();
        request.setSeat(Seat.B);
        request.setTable(getDummyTableList().get(0));  //random 1-5
        List<RequestType> reqTypes =new ArrayList();
        reqTypes.add(RequestType.values()[random(0,6)]);
        request.setRequestTypes(reqTypes);
        request.setTime(new Date());
        return request;
    }

    public int random(int minimum, int maximum){
        Random r = new Random();
        int i1 = r.nextInt(maximum - minimum) + minimum;
        return i1;
    }


    public Request getCurrRequest() {
        return currRequest;
    }

    public void setCurrRequest(Request currRequest) {
        this.currRequest = currRequest;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getCurrMessage() {
        return currMessage;
    }

    public void setCurrMessage(String currMessage) {
        this.currMessage = currMessage;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public Order getCurrOrder() {
        return currOrder;
    }

    public void setCurrOrder(Order currOrder) {
        this.currOrder = currOrder;
    }

    public int getCurOderPosition() {
        return curOderPosition;
    }

    public void setCurOderPosition(int curOderPosition) {
        this.curOderPosition = curOderPosition;
    }
}
