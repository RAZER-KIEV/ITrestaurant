package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
    private List<Request> requestList;
    private TextView time, tableSeat;
    private List<ImageView> imageViews;
    private Request request;
    private MyApplication myApplication;
    private View root;

    public RequestAdapter(Context context, List<Request> requestList) {
        this.context = context;
        this.requestList = requestList;
        lInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        root = lInflater.inflate(R.layout.messages_item, parent, false);
        //if (convertView == null) {
            //L.l("convertView == null", this);
            if(request.getRequestTypes().get(0)==RequestType.GetCash||request.getRequestTypes().get(0)==RequestType.Kitchen ){
            root.setBackground(ContextCompat.getDrawable(context, R.drawable.item_bckgrnd_red));
            }
       // }//else root = convertView;

        ((TextView)root.findViewById(R.id.time_tv)).setText(myApplication.getSimpleDateFormat().format(request.getTime()));
        ((TextView)root.findViewById(R.id.table_seat_tv)).setText(buildTableSeatStr());
        setImages();
        return root;
    }

    private void setImages(){
        int counter = 0;
        for(RequestType requestType: request.getRequestTypes()){
           switch (counter){
               case 0 : choseIcon((ImageView) root.findViewById(R.id.iv0), requestType);
                   break;
               case 1: choseIcon((ImageView)root.findViewById(R.id.iv1), requestType);
                   break;
               case 2: choseIcon((ImageView)root.findViewById(R.id.iv2), requestType);
                   break;
               case 3: choseIcon((ImageView)root.findViewById(R.id.iv3), requestType);
                   break;
               case 4: choseIcon((ImageView)root.findViewById(R.id.iv4), requestType);
                   break;
           }
           counter++;
        }
    }

    private void choseIcon(ImageView imageView, RequestType requestType){
        switch (requestType){
            case ComeToMe:
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_account));
                break;
            case Taxi:
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_car));
                break;
            case Cash:
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cash_multiple));
                break;
            case CreditCard:
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_credit_card));
                break;
            case Cutlery:
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_silverware_fork));
                break;
            case GetCash:
                imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cash_multiple));
                break;
            case Kitchen: imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_food));
                break;
        }
        imageView.setVisibility(View.VISIBLE);
    }

    private String buildTableSeatStr(){
        return ""+request.getTable().getNumber()+request.getSeat();
    }
}
