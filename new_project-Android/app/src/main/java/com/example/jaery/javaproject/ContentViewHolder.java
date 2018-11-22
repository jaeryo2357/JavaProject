package com.example.jaery.javaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentViewHolder extends RecyclerView.ViewHolder {
    public ContentViewHolder(@NonNull View itemView) {
        super(itemView);
        smallImage=itemView.findViewById(R.id.content_card_image);
        Title=itemView.findViewById(R.id.content_card_title);
        byname=itemView.findViewById(R.id.content_card_byname);

    }

    ImageView smallImage;
    TextView Title;
    TextView byname;
}
