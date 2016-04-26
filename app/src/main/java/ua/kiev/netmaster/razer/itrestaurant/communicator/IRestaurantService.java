package ua.kiev.netmaster.razer.itrestaurant.communicator;

import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Order;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;
import ua.kiev.netmaster.razer.itrestaurant.fragments.LikeACalcFragment;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 21-Apr-16.
 */
public interface IRestaurantService {
    Request getCurrRequest();

    void setCurrRequest(Request currRequest);

    List<Order> getOrderList();

    void setOrderList(List<Order> orderList);

    String getCurrMessage();

    void setCurrMessage(String currMessage);

    List<MenuItem> getMenuItemList();

    void setMenuItemList(List<MenuItem> menuItemList);

    Order getCurrOrder();

    void setCurrOrder(Order currOrder);

    int getCurOderPosition();

    void setCurOderPosition(int curOderPosition);

    LikeACalcFragment getLikeACalcFragment();

    void setLikeACalcFragment(LikeACalcFragment likeACalcFragment);

    double getCurChange();

    void setCurChange(double curChange);

    Drawable getInfoFrImg();

    void setInfoFrImg(Drawable infoFrImg);

    ArrayList<Request> getRequestList();

    void setRequestList(ArrayList<Request> requestList);

    SimpleDateFormat getSimpleDateFormat();

    List<Table> getDummyTableList();

    boolean isBackToRequesqList();

    void setBackToRequesqList(boolean backToRequesqList);

    void setDummyTableList(List<Table> dummyTableList);
}
