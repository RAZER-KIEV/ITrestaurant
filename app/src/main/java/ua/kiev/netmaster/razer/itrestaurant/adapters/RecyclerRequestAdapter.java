package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 18-Apr-16.
 */
public class RecyclerRequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


    private final int TYPE_MASSEGE=1, TYPE_GETCASH_SMALL=0, TYPE_MASSAGE_KITCHEN=2;
    private ArrayList<Request> requestList;
    private Context context;
    private MyApplication myApplication;
    private Request request;


    public RecyclerRequestAdapter(Context context,ArrayList<Request> requestList) {
        this.requestList = requestList;
        this.context =context;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==0){
           view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.getcash_small_item, parent,false);
           ViewHolderGetCash holder =  new ViewHolderGetCash(view);
           return holder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item, parent, false);
            ViewHolderMessage viewHolder = new ViewHolderMessage(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        request = requestList.get(position);
        switch (holder.getItemViewType()){
            case TYPE_GETCASH_SMALL :
                ViewHolderGetCash viewHolderGetCash = (ViewHolderGetCash) holder;
                configureVHGC(viewHolderGetCash);
                break;
            case TYPE_MASSEGE :
                ViewHolderMessage viewHolderMessage = (ViewHolderMessage) holder;
                configureVHM(viewHolderMessage);
                break;
        }
    }

    private void configureVHM(ViewHolderMessage viewHolderMessage) {
        if(request.getRequestTypes().element()==RequestType.Kitchen){
            viewHolderMessage.root.setBackground(ContextCompat.getDrawable(context, R.drawable.item_bckgrnd_red));
        }else viewHolderMessage.root.setBackground(ContextCompat.getDrawable(context, R.drawable.item_backgrnd));
        viewHolderMessage.time_tv.setText(myApplication.getSimpleDateFormat().format(request.getTime()));
        viewHolderMessage.table_seat_tv.setText(buildTableSeatStr());
        setImages(viewHolderMessage);
    }

    private void configureVHGC(ViewHolderGetCash viewHolderGetCash) {
        viewHolderGetCash.table_seat_tv.setText(buildTableSeatStr());
        viewHolderGetCash.time_tv.setText(myApplication.getSimpleDateFormat().format(request.getTime()));
    }

    @Override
    public int getItemViewType(int position) {
        if(requestList.get(position).getRequestTypes().element()== RequestType.GetCash){
            return TYPE_GETCASH_SMALL;
        }else return TYPE_MASSEGE;
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    class ViewHolderMessage extends RecyclerView.ViewHolder{
        View root;
        TextView time_tv, table_seat_tv;
        ImageView iv0, iv1,iv2, iv3;
        public ViewHolderMessage(View v) {
            super(v);
            root=v;
            time_tv = (TextView) v.findViewById(R.id.time_tv);
            table_seat_tv = (TextView) v.findViewById(R.id.table_seat_tv);
            iv0  = (ImageView) v.findViewById(R.id.iv0);
            iv1  = (ImageView) v.findViewById(R.id.iv1);
            iv2  = (ImageView) v.findViewById(R.id.iv2);
            iv3  = (ImageView) v.findViewById(R.id.iv3);
        }
    }

    class ViewHolderGetCash extends RecyclerView.ViewHolder{
        TextView time_tv, table_seat_tv;
        public ViewHolderGetCash(View v) {
            super(v);
            time_tv = (TextView) v.findViewById(R.id.time_tv_small);
            table_seat_tv = (TextView) v.findViewById(R.id.table_seat_tv_small);
        }
    }

    private String buildTableSeatStr(){
        return ""+request.getTable().getNumber()+request.getSeat();
    }

    private void setImages(ViewHolderMessage viewHolder){
        L.l("setImages()", this);
        makeEveryThingNull(viewHolder);
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

    private void makeEveryThingNull(ViewHolderMessage viewHolder){
        viewHolder.iv0.setImageDrawable(null);
        viewHolder.iv1.setImageDrawable(null);
        viewHolder.iv2.setImageDrawable(null);
        viewHolder.iv3.setImageDrawable(null);
    }
}
