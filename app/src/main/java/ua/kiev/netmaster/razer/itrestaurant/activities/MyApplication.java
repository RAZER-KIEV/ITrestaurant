package ua.kiev.netmaster.razer.itrestaurant.activities;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.communicator.IRestaurantService;
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Order;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.fragments.LikeACalcFragment;
import ua.kiev.netmaster.razer.itrestaurant.generator.Generator;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class MyApplication extends Application implements IRestaurantService {

    private List<Order> orderList;
    private ArrayList<Request> requestList;
    private SimpleDateFormat simpleDateFormat;
    private List<Table> dummyTableList;
    private int number=0;
    private Request currRequest;
    private String currMessage;
    private List<MenuItem> menuItemList;
    private Order currOrder;
    private int curOderPosition;
    private LikeACalcFragment likeACalcFragment;
    private double curChange;
    private Drawable infoFrImg;
    private boolean backToRequesqList;
    private DisplayMetrics displayMetrics;
    public static IRestaurantService iRestaurantService;


    @Override
    public void onCreate() {
        L.l("onCreate", this);
        super.onCreate();
        simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
        iRestaurantService = this;
        new Generator(this).generate();
    }

    public void commitFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        String str[]=fragment.getClass().toString().split("\\.");
        String fragmentName = str[str.length-1].trim();
        L.l(" MYAPPLICATION. commitFragment. - " + fragmentName);
        fragmentTransaction.replace(R.id.processing_container, fragment, fragmentName);
        fragmentTransaction.addToBackStack("BackTag");
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    public void setToolbarTitle(String title,Activity activity ){
        L.l("setToolbarTitle()");
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public void setDummyTableList(List<Table> dummyTableList) {
        this.dummyTableList = dummyTableList;
    }


    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public ImageView choseIcon(ImageView imageView, RequestType requestType){
        switch (requestType){
            case ComeToMe:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_account));
                break;
            case Taxi:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_car));
                break;
            case Cash:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_cash_multiple));
                break;
            case CreditCard:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_credit_card));
                break;
            case Cutlery:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_silverware_fork));
                break;
            case GetCash:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_cash_multiple));
                break;
            case Kitchen:
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_food));
                break;
        }
        imageView.setVisibility(View.VISIBLE);
        return imageView;
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

    public LikeACalcFragment getLikeACalcFragment() {
        return likeACalcFragment;
    }

    public void setLikeACalcFragment(LikeACalcFragment likeACalcFragment) {
        this.likeACalcFragment = likeACalcFragment;
    }

    public double getCurChange() {
        return curChange;
    }

    public void setCurChange(double curChange) {
        this.curChange = curChange;
    }

    public Drawable getInfoFrImg() {
        return infoFrImg;
    }

    public void setInfoFrImg(Drawable infoFrImg) {
        this.infoFrImg = infoFrImg;
    }

    public ArrayList<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList<Request> requestList) {
        this.requestList = requestList;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }
    public List<Table> getDummyTableList() {
        return dummyTableList;
    }

    public boolean isBackToRequesqList() {
        return backToRequesqList;
    }

    public void setBackToRequesqList(boolean backToRequesqList) {
        this.backToRequesqList = backToRequesqList;
    }

    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    public void setDisplayMetrics(DisplayMetrics displayMetrics) {
        this.displayMetrics = displayMetrics;
    }
}
