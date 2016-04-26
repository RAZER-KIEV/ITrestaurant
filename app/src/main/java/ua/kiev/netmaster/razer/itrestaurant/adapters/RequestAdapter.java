package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class RequestAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<Request> requestList;
    private Request request;
    private MyApplication myApplication;
    private ViewHolder viewHolder;

    public RequestAdapter(Context context, ArrayList<Request> requestList) {
        this.context = context;
        this.requestList = requestList;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return requestList.size();
    }

    @Override
    public Object getItem(int position) {
        return requestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        L.l("getView()", this);

        request = (Request) getItem(position);
        L.l("request = "+ request);
        L.l("position = "+ position);
        //root = lInflater.inflate(R.layout.messages_item, parent, false);
        if (convertView == null) {
            L.l("convertView == null");
            lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lInflater.inflate(R.layout.messages_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            L.l("convertView != null");
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(request.getRequestTypes() != null && (request.getRequestTypes().element()==RequestType.GetCash || request.getRequestTypes().element()==RequestType.Kitchen )){
            convertView.setBackground(ContextCompat.getDrawable(context, R.drawable.item_bckgrnd_red));
        }else  convertView.setBackground(ContextCompat.getDrawable(context, R.drawable.item_backgrnd));
        setImages();
        viewHolder.time_tv.setText(myApplication.getSimpleDateFormat().format(request.getTime()));
        viewHolder.table_seat_tv.setText(buildTableSeatStr());


        L.l("request = " +request, this);
        return convertView;
    }

    private void setImages(){
        L.l("setImages()", this);
        makeEveryThingNull();
        int counter = 0;
        for(RequestType requestType: request.getRequestTypes()){
           switch (counter){
               case 0 : myApplication.choseIcon(viewHolder.iv0, requestType);
                   break;
               case 1: myApplication.choseIcon(viewHolder.iv1, requestType);
                   break;
               case 2: myApplication.choseIcon(viewHolder.iv2, requestType);
                   break;
               case 3: myApplication.choseIcon(viewHolder.iv3, requestType);
                   break;
           }
           counter++;
        }
    }

    private void makeEveryThingNull(){
        viewHolder.iv0.setImageDrawable(null);
        viewHolder.iv1.setImageDrawable(null);
        viewHolder.iv2.setImageDrawable(null);
        viewHolder.iv3.setImageDrawable(null);
    }

    private String buildTableSeatStr(){
        return ""+request.getTable().getNumber()+request.getSeat();
    }

    class ViewHolder{
        TextView time_tv, table_seat_tv;
        ImageView iv0, iv1,iv2, iv3;
        public ViewHolder(View v) {
            time_tv = (TextView) v.findViewById(R.id.time_tv);
            table_seat_tv = (TextView) v.findViewById(R.id.table_seat_tv);
            iv0  = (ImageView) v.findViewById(R.id.iv0);
            iv1  = (ImageView) v.findViewById(R.id.iv1);
            iv2  = (ImageView) v.findViewById(R.id.iv2);
            iv3  = (ImageView) v.findViewById(R.id.iv3);
        }
    }
}
