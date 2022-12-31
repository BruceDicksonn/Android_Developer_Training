package com.example.recyclerview2.listener;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onLongItemClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {

        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(),e.getY());

                /** Sempre verificar se o child e o listener são diferentes de null **/
                if(mListener != null && child != null){

                    /** Define o listener passando o elemento pressionado e a posição do mesmo **/
                    mListener.onItemClick(child, recyclerView.getChildAdapterPosition(child));

                }

                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {

                /** Busca o elemento filho que foi pressionado passando seus eixos de posição **/
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if(child != null && mListener != null) {

                    /** Define o listener passando o elemento pressionado e a posição do mesmo **/
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));

                }

                super.onLongPress(e);
            }
        });

    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent e) {

        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());


        /** Sempre verificar se todos os elementos são diferentes de nulo **/
        if(childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){

            mListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
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
