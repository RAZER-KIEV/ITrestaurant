package ua.kiev.netmaster.razer.itrestaurant.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 19-Apr-16.
 */
public class ReecyclerItemClickListener implements RecyclerView.OnItemTouchListener{

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public ReecyclerItemClickListener(Context context, OnItemClickListener listener) {
        L.l("onInterceptTouchEvent", this);
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        L.l("onInterceptTouchEvent", this);
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            L.l("returns true");
            return true;
        }
        L.l("returns false");
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
