package com.example.jaery.javaproject;


import android.app.Activity;
import android.content.Intent;
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

    public void addItem(boolean change,int resource,String title, String byname, String relase, Bitmap b)
    {
        WebToonItem webToonItem=new WebToonItem(resource,title,byname,relase,b);
        arrayList.add(webToonItem);
    }

    public void addItem(int resource,Bitmap b)
    {
        WebToonItem webToonItem=new WebToonItem(resource,b);
        arrayList.add(webToonItem);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {

                convertView = context.getLayoutInflater().inflate(arrayList.get(position).getResource(), parent, false);
                holder=connectResource(convertView,arrayList.get(position).getResource());

                convertView.setTag(holder);


        }
        else
        {
            holder = (ViewHolder) convertView.getTag();



        }
        if(holder.byname!=null)
        holder.byname.setText(arrayList.get(position).getByname());
        if(holder.text !=null)
        holder.text.setText(arrayList.get(position).getTitle());
        if(holder.timestamp !=null)
        holder.timestamp.setText(arrayList.get(position).getRelease());
        holder.icon.setImageBitmap(arrayList.get(position).getIcon());

        return convertView;
    }

    public ViewHolder connectResource(View convertView,int resource){
            ViewHolder holder=new ViewHolder();
        switch (resource)
        {
            case R.layout.webtoon_list_item:

                holder.byname=convertView.findViewById(R.id.webtoon_byname);
                holder.icon=convertView.findViewById(R.id.Webtoon_Image);
                holder.text=convertView.findViewById(R.id.webtoon_title);
                holder.timestamp=convertView.findViewById(R.id.webtoon_up_time);

                break;

            case R.layout.webtoon_view_item:


                holder.icon=convertView.findViewById(R.id.view_list_image);
                break;
        }
        return holder;
    }


    static class ViewHolder {

        TextView text;
        TextView timestamp;
        ImageView icon;
        TextView byname;

    }
}


