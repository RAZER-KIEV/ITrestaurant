package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.kiev.netmaster.razer.itrestaurant.R;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class TableMapFragmant extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.table_map_fragment_layout, container, false);
    }


}
