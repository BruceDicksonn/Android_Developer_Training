package com.example.apprecyclerviewchallenge.listener;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    OnItemClickListener mListener;
    GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
        void OnLongItemClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, RecyclerView recyclerView, OnItemClickListener listener){

        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(),e.getY());

                if(mListener != null && child != null) {

                    mListener.OnItemClick(child, recyclerView.getChildAdapterPosition(child));
                    return true;

                }

                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(),e.getY());

                if(mListener != null && child != null) {

                    mListener.OnLongItemClick(child, recyclerView.getChildAdapterPosition(child));

                }

                super.onLongPress(e);
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent e) {

        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

        if(child != null && mListener != null && mGestureDetector.onTouchEvent(e)){

            mListener.OnItemClick(child, recyclerView.getChildAdapterPosition(child));
            return true;

        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
