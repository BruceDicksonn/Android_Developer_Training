package com.example.apprecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private LinkedList<String> mWordList;
    private LayoutInflater minInflater;

    public WordListAdapter(Context context, LinkedList<String> wordList){
        minInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = minInflater.inflate(R.layout.wordlist_item, viewGroup, false);

        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String mCurrent = mWordList.get(i);
        viewHolder.wordItemView.setText(mCurrent);

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView wordItemView;
        final WordListAdapter mAdapter;


        public ViewHolder(@NonNull View itemView, WordListAdapter mAdapter) {
            super(itemView);

            this.wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = mAdapter;

            itemView.setOnClickListener(this);

        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onClick(View view) {

            int mPosition = getLayoutPosition();

            String element = mWordList.get(mPosition);
            mWordList.set(mPosition, "Clicked! " + element);

            mAdapter.notifyDataSetChanged();

        }
    }
}
