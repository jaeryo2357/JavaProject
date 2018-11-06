package com.example.jaery.javaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.PipedOutputStream;
import java.util.ArrayList;


class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private ArrayList<WebToonItem> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImage;
        public TextView mTitle;
        public TextView mByName;
        public TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mImage = (ImageView)view.findViewById(R.id.cardview_image);
            mTitle = (TextView)view.findViewById(R.id.cardview_title);
            mTime=(TextView)view.findViewById(R.id.cardview_time);
            mByName=(TextView)view.findViewById(R.id.cardview_byname);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CardViewAdapter(ArrayList<WebToonItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitle.setText(mDataset.get(position).getTitle());
        holder.mImage.setImageBitmap(mDataset.get(position).getIcon());
        holder.mByName.setText(mDataset.get(position).getByname());
        holder.mTime.setText(mDataset.get(position).getRelease());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}




