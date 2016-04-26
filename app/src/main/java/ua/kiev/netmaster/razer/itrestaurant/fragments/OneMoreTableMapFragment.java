package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.activities.ProccessingActivity;
import ua.kiev.netmaster.razer.itrestaurant.communicator.IRestaurantService;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 25-Apr-16.
 */
public class OneMoreTableMapFragment extends Fragment implements View.OnDragListener, View.OnLongClickListener, View.OnTouchListener, View.OnClickListener {

    Button button;
    int x_cord=0, y_cord=0;
    DisplayMetrics metrics;
    RelativeLayout relLayout;
    private int tableIconSize;
    private final String ButtonTag = "ButtonTag";


    private RelativeLayout.LayoutParams layoutParams;

    MyApplication myApplication;
    IRestaurantService iRestaurantService;

    //ClipData dragData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.table_map_fragment_layout, container, false);
        relLayout = (RelativeLayout) root.findViewById(R.id.dragLayout);
        relLayout.setOnDragListener(this);
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iRestaurantService  = (IRestaurantService) getActivity().getApplication();
        myApplication = (MyApplication) getActivity().getApplication();
        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        myApplication.setDisplayMetrics(metrics);
        L.l("metrics.heightPixels = " + metrics.heightPixels);
        L.l("metrics.widthPixels = " + metrics.widthPixels);
        L.l("metrics.densityDpi = " + metrics.densityDpi);
        L.l("metrics.density = " + metrics.density);
        L.l("metrics.xdpi = " + metrics.xdpi);
        L.l("metrics.xdpi = " + metrics.ydpi);
        L.l("metrics.scaledDensity = " + metrics.scaledDensity);
        tableIconSize = metrics.widthPixels/10;
        makeButtons();
    }


    private void makeButtons(){
        int merginLeft=0, merginTop=0;
        for(Table table : iRestaurantService.getDummyTableList()){
            button =  new Button(getContext());
            button.setTag(ButtonTag + table.getNumber());
            button.setText("" + table.getNumber());
            button.setBackground(ContextCompat.getDrawable(getContext(), R.mipmap.table_icon));
            button.setOnLongClickListener(this);
            button.setOnClickListener(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(tableIconSize, tableIconSize);
            params.leftMargin = merginLeft;
            params.topMargin = merginTop;
            button.setLayoutParams(params);
            merginLeft+=tableIconSize;
            merginTop+=tableIconSize+10;
            relLayout.addView(button);
            L.l("merginLeft = "+ merginLeft+"; merginTop =  "+merginTop);
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        //v.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        L.l("view = "+v.getClass().getName(), this);
        switch(event.getAction())
        {
            case DragEvent.ACTION_DRAG_STARTED:
                L.l("Action is DragEvent.ACTION_DRAG_STARTED", this);
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                L.l("Action is DragEvent.ACTION_DRAG_ENTERED", this);
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                L.l("x_cord = "+x_cord+"; y_cord = "+y_cord ,this);
                break;

            case DragEvent.ACTION_DRAG_EXITED :
                L.l("Action is DragEvent.ACTION_DRAG_EXITED", this);
                break;

            case DragEvent.ACTION_DRAG_LOCATION  :
                L.l("Action is DragEvent.ACTION_DRAG_LOCATION", this);
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                L.l("x_cord = "+x_cord+"; y_cord = "+y_cord ,this);
                break;

            case DragEvent.ACTION_DRAG_ENDED   :
                L.l("Action is DragEvent.ACTION_DRAG_ENDED", this);
                L.l("x_cord = "+x_cord+"; y_cord = "+y_cord ,this);
                layoutParams = new RelativeLayout.LayoutParams(96, 96);//RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = x_cord-48;
                layoutParams.topMargin = y_cord-48;
                button.setLayoutParams(layoutParams);
                break;

            case DragEvent.ACTION_DROP:
                L.l("ACTION_DROP event", this);
                break;
            default: break;
        }
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        ClipData dragData = new ClipData(v.getTag().toString(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },item);
        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
        button = (Button) v;
        button.startDrag(dragData, myShadow, null, 0);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        L.l("onTouch:  v.getClass().getName() = "+ v.getClass().getName() , this);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            button.startDrag(data, shadowBuilder, button, 0);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        int tablenumber = Integer.parseInt(((Button)v).getText().toString());
        Intent intent  = new Intent(getContext(), ProccessingActivity.class);
        intent.putExtra("pos",tablenumber);
        startActivity(intent);
        // TODO: 26-Apr-16
    }
}
