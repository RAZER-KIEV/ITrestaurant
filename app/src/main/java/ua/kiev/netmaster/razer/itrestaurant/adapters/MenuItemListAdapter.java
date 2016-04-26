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
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.OrderItem;

/**
 * Created by RAZER on 09-Apr-16.
 */
public class MenuItemListAdapter extends BaseAdapter{
    private List<MenuItem> menuItems;
    private MyApplication myApplication;
    private View root;
    private LayoutInflater lInflater;

    public MenuItemListAdapter(List<MenuItem> menuItems, Context context) {
        this.menuItems = menuItems;
        myApplication = (MyApplication) context.getApplicationContext();
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItem menuItem = (MenuItem) getItem(position);
        root = lInflater.inflate(R.layout.dishes_item_lay, parent, false);
        ((TextView)root.findViewById(R.id.dishNameTv)).setText(menuItem.getName());
        ((TextView)root.findViewById(R.id.dishPriceTv)).setText(String.valueOf(menuItem.getPrice()));
        return root;
    }
}
