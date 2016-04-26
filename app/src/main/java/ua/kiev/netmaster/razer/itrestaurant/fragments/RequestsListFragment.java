package ua.kiev.netmaster.razer.itrestaurant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.adapters.RequestAdapter;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class RequestsListFragment extends Fragment implements AdapterView.OnItemClickListener {

   // private SecondPageFragmentListener listener;
    private ListView listView;
    private RequestAdapter requestAdapter;
    private MyApplication myApplication;
    private static SecondPageFragmentListener spfListener;


    public static RequestsListFragment newInstance(SecondPageFragmentListener listener) {
        spfListener = listener;
        Bundle args = new Bundle();
        RequestsListFragment fragment = new RequestsListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.request_list_fragment_lay, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        myApplication.setToolbarTitle(myApplication.getString(R.string.requests), getActivity());
        listView = (ListView) getActivity().findViewById(R.id.requestList);
        requestAdapter = new RequestAdapter(getContext(), myApplication.getRequestList());
        listView.setAdapter(requestAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        myApplication.setCurrRequest(myApplication.getRequestList().get(position));
        L.l("myApplication.getCurrRequest().toString() " + myApplication.getCurrRequest().toString(), this);
        spfListener.onSwitchToChildFragment();
    }

    public static SecondPageFragmentListener getSpfListener() {
        return spfListener;
    }
}
