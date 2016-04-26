package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.communicator.IRestaurantService;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;

/**
 * Created by RAZER on 26-Apr-16.
 */
public class PlaceDetailsFragment extends Fragment implements View.OnClickListener {
    private IRestaurantService iRestaurantService;
    private MyApplication myApplication;
    private View root;
    private ImageView placeIV1, placeIV2, placeIV3, placeIV4;
    private final String requestPos="requestPos";
    private Request curRequest;
    private TextView tableSeat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root  = inflater.inflate(R.layout.place_details_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iRestaurantService = (IRestaurantService) getActivity().getApplication();
        myApplication = (MyApplication) getActivity().getApplication();
        curRequest = myApplication.getCurrRequest();
        initViews();
        setImages();
    }

    private void setImages(){
        int counter = 0;
        if(curRequest==null) return;
        for( RequestType type :curRequest.getRequestTypes()){
            if(counter==0){
                myApplication.choseIcon(placeIV1, type);
                counter++;
            }else if(counter==1){
                myApplication.choseIcon(placeIV2, type);
                counter++;
            }else if(counter==2){
                myApplication.choseIcon(placeIV3, type);
                counter++;
            }else if (counter==3){
                myApplication.choseIcon(placeIV4, type);
                counter++;
            }
        }
        if(counter==0){
            placeIV1.setMinimumHeight(myApplication.getDisplayMetrics().widthPixels/2);
            placeIV1.setMinimumWidth(myApplication.getDisplayMetrics().widthPixels/2);
        }
    }

    private  void initViews(){
        //int size = myApplication.getDisplayMetrics().widthPixels/2;
        tableSeat = (TextView) root.findViewById(R.id.tableSeatTV);
        tableSeat.setText(""+curRequest.getTable().getNumber()+curRequest.getSeat());
        placeIV1 = (ImageView) root.findViewById(R.id.placeIV1);
        placeIV1.setClickable(true);
        placeIV1.setOnClickListener(this);
        placeIV2 = (ImageView) root.findViewById(R.id.placeIV2);
        placeIV3 = (ImageView) root.findViewById(R.id.placeIV3);
        placeIV4 = (ImageView) root.findViewById(R.id.placeIV4);
    }

    @Override
    public void onClick(View v) {
        myApplication.commitFragment(new RequestDescriptionFragment(), getFragmentManager());
    }
}
