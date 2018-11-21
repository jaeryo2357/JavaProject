package com.example.jaery.javaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class GenreViewHolder extends RecyclerView.ViewHolder {

    public TextView genre;
    public boolean Click;


    public GenreViewHolder(View itemView) {
        super(itemView);
        Click=false;
        genre=itemView.findViewById(R.id.genre_tv);
    }
}
