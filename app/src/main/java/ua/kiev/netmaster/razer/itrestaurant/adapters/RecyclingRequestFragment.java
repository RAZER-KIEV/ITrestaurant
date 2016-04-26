package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.fragments.SecondPageFragmentListener;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 18-Apr-16.
 */
public class RecyclingRequestFragment extends Fragment{

    private MyApplication myApplication;
    private RecyclerRequestAdapter recyclerRequestAdapter;
    private RecyclerView recyclerView;
    private static SecondPageFragmentListener spfListener;
    private int counter, spans;
    List<Integer> spansizes;

    public static RecyclingRequestFragment newInstance(SecondPageFragmentListener listener) {
        spfListener = listener;
        return new RecyclingRequestFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recyclerview_lay, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list1);
        //XmlPullParser parser = getResources().getXml(R.id.list1);
        //AttributeSet attributes = Xml.asAttributeSet(parser);
        //MyRecyclerView myRecyclerView = new MyRecyclerView(getContext(),)
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        recyclerRequestAdapter = new RecyclerRequestAdapter(getContext(),myApplication.getRequestList());
        GridLayoutManager manager = new GridLayoutManager(getContext(),spans = calculateSpans());
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
               return spansizes.get(position);
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new ReecyclerItemClickListener(getContext(), new ReecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                myApplication.setCurrRequest(myApplication.getRequestList().get(position));
                L.l("myApplication.getCurrRequest().toString() " + myApplication.getCurrRequest().toString(), this);
                spfListener.onSwitchToChildFragment();
            }
        }));
        recyclerView.setAdapter(recyclerRequestAdapter);
    }
    private int calculateSpans(){
        counter=0;
        for (Request request :myApplication.getRequestList()){
            if(request.getRequestTypes().element()== RequestType.GetCash)counter++;
        }
        fillMap();
        return 4;
    }

    private void fillMap() {
        L.l("fillMap()");
        spansizes = new ArrayList<>();
        int span4rows = counter/4;
        for(int i=0; i<span4rows*4; i++){
            spansizes.add(1);
        }
        int other = counter%4;
        if(other==3){
            spansizes.add(2);
            spansizes.add(2);
            spansizes.add(4);
        }
        if(other == 2){
            spansizes.add(2);
            spansizes.add(2);
        }if (other==1){
            spansizes.add(4);
        }
        L.l("myApplication.getRequestList().size() = "+myApplication.getRequestList().size(), this);
        L.l("spansizes.size()");
        int curSize = spansizes.size();
        for (int i=0; i<myApplication.getRequestList().size()-curSize; i++){
            spansizes.add(4);
        }
        L.l("spansizes.size() = "+spansizes.size());
    }
}
