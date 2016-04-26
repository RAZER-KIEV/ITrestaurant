package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;
import java.util.zip.Inflater;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;

/**
 * Created by RAZER on 24-Apr-16.
 */
public class TableMapGridAdapter extends BaseAdapter {
    private List<Table> tableList;
    private Context context;

    public TableMapGridAdapter(List<Table> tableList, Context context) {
        this.tableList = tableList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tableList.size();
    }

    @Override
    public Object getItem(int position) {
        return tableList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Button button = (Button) inflater.inflate(R.layout.table_map_item_lay, null);
        button.setText(""+tableList.get(position).getNumber());
        return button;
    }
}
