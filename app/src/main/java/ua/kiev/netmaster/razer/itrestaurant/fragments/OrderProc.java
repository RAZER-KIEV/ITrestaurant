package ua.kiev.netmaster.razer.itrestaurant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.adapters.OrderItemListAdapter;
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Order;
import ua.kiev.netmaster.razer.itrestaurant.entities.OrderItem;
import ua.kiev.netmaster.razer.itrestaurant.enums.OrderStatus;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderProc extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MyApplication myApplication;
    private TextView messageTv, discountTv , tipsTv;
    private Button addItemBT, summBt, printRecipeBt, addOrder;
    private View rootV;
    private Order currOrder;
    private ListView ordersItemsListView;
    private OrderItemListAdapter adapter;
    private double price;


    public OrderProc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootV = inflater.inflate(R.layout.fragment_order_proc, container, false);
        return rootV;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        searchOrder();
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.l("onResume() = " + currOrder, this);
        if(currOrder.getItems()!=null) {
            adapter.setOrderItems(Arrays.asList(currOrder.getItems()));
            L.l("adapter.getCount() = " + adapter.getCount());
            adapter.notifyDataSetChanged();
            summBt.setText(String.format(" $ %.2f", calcSum()));
            discountTv.setText(String.format(" $ %.2f", price / 10));
            tipsTv.setText(String.format(" $ %.2f", price / 20));
        }
    }

    private  void init(){
        messageTv = (TextView) rootV.findViewById(R.id.messageTv);
            messageTv.setText(myApplication.getCurrMessage());
        discountTv = (TextView) rootV.findViewById(R.id.discountTv);
        tipsTv = (TextView) rootV.findViewById(R.id.tipsTv);
        if(currOrder!=null){
        ordersItemsListView = (ListView) rootV.findViewById(R.id.ordersItemsListView);
            if(currOrder.getItems()!=null&& currOrder.getItems().length>0){
                ordersItemsListView.setAdapter(adapter = new OrderItemListAdapter(Arrays.asList(currOrder.getItems()), getContext()));
            }else ordersItemsListView.setAdapter( adapter = new OrderItemListAdapter(new ArrayList<OrderItem>(), getContext()));
        ordersItemsListView.setOnItemClickListener(this);}
        addItemBT = (Button) rootV.findViewById(R.id.addItemBT);
            addItemBT.setOnClickListener(this);
        summBt = (Button) rootV.findViewById(R.id.summBt);
            summBt.setOnClickListener(this);
        printRecipeBt = (Button) rootV.findViewById(R.id.printRecipeBt);
            printRecipeBt.setOnClickListener(this);
        addOrder = (Button) rootV.findViewById(R.id.addOrderBt);
            addOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addItemBT :
                myApplication.commitFragment( new MenuItemChooser(),getFragmentManager());
                break;
            case R.id.summBt :
                break;
            case R.id.printRecipeBt:
                L.t("Printing...", getActivity());
                break;
            case R.id.addOrderBt:
                break;
        }
    }

    private void searchOrder(){
        List<Order> orders = myApplication.getOrderList();
        if(orders!=null&&orders.size()>0){
        int counter = 0;
        for ( Order order : myApplication.getOrderList()){
            if(order.getTable()== myApplication.getCurrRequest().getTable()&& order.getSeat() == myApplication.getCurrRequest().getSeat()){
                currOrder = order;
                myApplication.setCurOderPosition(counter);
                break;
                }
            counter++;
            }
        }else {
            //create first order
            Order neworder = new Order();
            neworder.setTable(myApplication.getCurrRequest().getTable());
            neworder.setSeat(myApplication.getCurrRequest().getSeat());
            neworder.setStatus(OrderStatus.OPEN);
            //neworder.setOrders(new Order[]{neworder});
            orders = new ArrayList<>();
            orders.add(neworder);
            currOrder = neworder;
            myApplication.setOrderList(orders);
            myApplication.setCurOderPosition(0);
        }
        myApplication.setCurrOrder(currOrder);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.t("TODO: 09-Apr-16 Go to ready Fragment ", getActivity());
        myApplication.commitFragment(new ReadyFragment(), getFragmentManager());
        // TODO: 09-Apr-16 Go to ready Fragment
    }


    private double calcSum(){
        price=0d;
        if(currOrder.getItems()==null|| currOrder.getItems().length<1) return 0.0d;
        for(OrderItem orderItem : currOrder.getItems()){
            price+=orderItem.getMenuItem().getPrice();
        }
        return price;
    }
}
