package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.OrderItem;

/**
 * Created by RAZER on 09-Apr-16.
 */
public class OrderItemListAdapter extends BaseAdapter {
    private Context context;
    private List<OrderItem> orderItems;
    private MyApplication myApplication;
    private View root;
    private LayoutInflater lInflater;

    public OrderItemListAdapter(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        myApplication = (MyApplication) context.getApplicationContext();
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public Object getItem(int position) {
        return orderItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItem orderItem = (OrderItem) getItem(position);
        root = lInflater.inflate(R.layout.dishes_item_lay, parent, false);
        ((TextView)root.findViewById(R.id.dishNameTv)).setText(orderItem.getName());
        ((TextView)root.findViewById(R.id.dishPriceTv)).setText(String.valueOf(orderItem.getPrice()));
        return root;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
