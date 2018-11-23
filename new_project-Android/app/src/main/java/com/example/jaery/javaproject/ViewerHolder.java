package com.example.jaery.javaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class ViewerHolder extends RecyclerView.ViewHolder {
    public ViewerHolder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.view_list_image);
    }

    ImageView image;
}
