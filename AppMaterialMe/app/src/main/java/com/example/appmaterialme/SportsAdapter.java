package com.example.appmaterialme;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Sport> mSportsData;
    private Context mContext;

    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;
    }


    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Sport currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport){
            // Populate the textviews with data.
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentSport.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {

            Sport currentSport = mSportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, ActivityDetail.class);

            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource",
                    currentSport.getImageResource());

            Base.trocar(
                    mSportsImage,
                    mSportsImage,
                    detailIntent,
                    mContext
            );

        }
    }
}

