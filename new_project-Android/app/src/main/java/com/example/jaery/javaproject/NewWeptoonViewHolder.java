package com.example.jaery.javaproject;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewWeptoonViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public ImageView Image;
    public TextView time;
    public TextView content;
    public TextView Title;


    public NewWeptoonViewHolder(View view) {
        super(view);

        Title=(TextView)view.findViewById(R.id.cardview_title) ;
        Image = (ImageView) view.findViewById(R.id.cardview_image);
        time = (TextView)view.findViewById(R.id.cardview_time);
        content = (TextView)view.findViewById(R.id.cardview_byname);
    }
}
