package com.example.jaery.javaproject;

import android.app.Activity;
import android.support.annotation.NonNull;
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


class WebtoonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<WebToonItem> mDataset;
    private Activity activity;
    ButtonClickListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder



    // Provide a suitable constructor (depends on the kind of dataset)
    public WebtoonAdapter(ArrayList<WebToonItem> myDataset, Activity activity,ButtonClickListener listener) {
        mDataset = myDataset;
        this.activity=activity;
        this.listener=listener;
    }


    // Replace the contents of a view (invoked by the layout manager)

    public interface ButtonClickListener {

        void ContentOnClick(View v,int position);

        void MapOnClick(View v,int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder=null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.cardview, viewGroup, false);
                viewHolder=new NewWeptoonViewHolder(v1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                NewWeptoonViewHolder vh1 = (NewWeptoonViewHolder) viewHolder;
                configureNewWebtoon_ViewHolder(vh1, position);
                break;


        }
    }


    public void addMenu(WebToonItem item)
    {
        mDataset.add(item);
    }
    @Override
    public int getItemViewType(int position) {

        return mDataset.get(position).Type;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    private void configureNewWebtoon_ViewHolder(NewWeptoonViewHolder vh1, int position) {
        vh1.content.setText(mDataset.get(position).getByname());
        vh1.Title.setText(mDataset.get(position).getTitle());
        vh1.time.setText(mDataset.get(position).getRelease());
        vh1.Image.setImageBitmap(mDataset.get(position).getIcon());
    }

}




