package com.example.jaery.javaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NotHolder extends RecyclerView.ViewHolder {

    public NotHolder(@NonNull View itemView) {
        super(itemView);

        Title=itemView.findViewById(R.id.webtoon_title);
        times=itemView.findViewById(R.id.webtoon_byname);
        Release=itemView.findViewById(R.id.webtoon_up_time);
    }

    TextView Title;
    TextView Release;
    TextView times;
}
