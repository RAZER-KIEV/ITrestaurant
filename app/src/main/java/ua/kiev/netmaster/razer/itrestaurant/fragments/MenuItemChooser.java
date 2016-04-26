package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.adapters.MenuItemListAdapter;
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Order;
import ua.kiev.netmaster.razer.itrestaurant.entities.OrderItem;
import ua.kiev.netmaster.razer.itrestaurant.enums.OrderItemStatus;
import ua.kiev.netmaster.razer.itrestaurant.enums.OrderStatus;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuItemChooser extends Fragment implements AdapterView.OnItemClickListener {

    private MyApplication myApplication;
    private ListView listView;
    private View root;


    public MenuItemChooser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_menu_item_chooser, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        listView = (ListView) root.findViewById(R.id.dishesListView);
        listView.setAdapter(new MenuItemListAdapter(myApplication.getMenuItemList(),getContext()));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MenuItem menuItem = myApplication.getMenuItemList().get(position);
        //myApplication.getCurrOrder().getItems()
        Order curOrd = myApplication.getCurrOrder();
        if(curOrd==null){
            Order neworder = new Order();
            neworder.setSeat(myApplication.getCurrRequest().getSeat());
            neworder.setTable(myApplication.getCurrRequest().getTable());
            neworder.setStatus(OrderStatus.OPEN);
            myApplication.setCurrOrder(neworder);
            curOrd = neworder;
        }
        OrderItem[] orderItems = curOrd.getItems();
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setStatus(OrderItemStatus.NEW);
        if(orderItems==null){
            orderItems = new OrderItem[1];
        }else orderItems =  Arrays.copyOf(orderItems, orderItems.length+1);
        orderItems[orderItems.length-1] = orderItem;
        curOrd.setItems(orderItems);
        myApplication.setCurrOrder(curOrd);
        List<Order> orders = myApplication.getOrderList();
        if(orders==null || orders.size()<1){
            orders = new ArrayList<>();
            orders.add(curOrd);
        }else {
            orders.remove(myApplication.getCurOderPosition());
            orders.add(myApplication.getCurOderPosition(), curOrd);
        }
        myApplication.setOrderList(orders);
        myApplication.commitFragment(ReadyFragment.newInstance(false), getFragmentManager());
    }
}
