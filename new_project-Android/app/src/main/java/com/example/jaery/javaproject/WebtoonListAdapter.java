package com.example.jaery.javaproject;


import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import java.util.ArrayList;

public class WebtoonListAdapter extends ArrayAdapter<WebToonItem> {
     private final Activity context;

     private  ArrayList<WebToonItem> arrayList;
    public WebtoonListAdapter(Activity context) {
        super(context,R.layout.webtoon_list_item);
        this.context = context;
        arrayList=new ArrayList<WebToonItem>();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public WebToonItem getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addItem(String title, String byname, String relase, Bitmap b)
    {
        WebToonItem webToonItem=new WebToonItem(title,byname,relase,b);
        arrayList.add(webToonItem);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.webtoon_list_item, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.Webtoon_Image);
            holder.text = (TextView) convertView.findViewById(R.id.webtoon_title);
            holder.timestamp = (TextView) convertView.findViewById(R.id.webtoon_up_time);
            holder.byname = (TextView) convertView.findViewById(R.id.webtoon_byname);
            convertView.setTag(holder);
        }

       /* ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(getItem(position));
                */
        return convertView;
    }
    static class ViewHolder {
        TextView text;
        TextView timestamp;
        ImageView icon;
        TextView byname;
        int position;
    }
}


