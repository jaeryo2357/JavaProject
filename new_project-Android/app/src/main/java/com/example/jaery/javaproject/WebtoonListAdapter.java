package com.example.jaery.javaproject;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;

import android.widget.TextView;

import java.util.ArrayList;

public class WebtoonListAdapter extends ArrayAdapter<WebToonItem> {
     private final Activity context;

     private static final int ITEM_VIEW_TYPE=0;
     private static final int ITEM_LIST_TYPE=1;
     private static final int ITEM_TYPE_MAX=2;
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
    public int getViewTypeCount(){
        return ITEM_TYPE_MAX;
    }

    @Override
    public int getItemViewType(int position)
    {
        return arrayList.get(position).getType();
    }

    @Override
    public WebToonItem getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addItem(int resource,String title, String byname, String relase, Bitmap b)
    {
        WebToonItem webToonItem=new WebToonItem(ITEM_LIST_TYPE,title,byname,relase,b);
        arrayList.add(webToonItem);
    }

    public void addItem(int resource,Bitmap b)
    {
        WebToonItem webToonItem=new WebToonItem(ITEM_VIEW_TYPE,b);
        arrayList.add(webToonItem);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        int viewType=getItemViewType(position);
        WebToonItem webToonItem=arrayList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            int layout=0;
            switch (viewType)
            {
                case ITEM_LIST_TYPE:
                    layout=R.layout.webtoon_list_item;
                    break;
                case ITEM_VIEW_TYPE:
                    layout=R.layout.webtoon_view_item;
                    break;
            }

            convertView=inflater.inflate(layout,parent,false);



           holder=connectResource(convertView,viewType);


        }

        try {
            holder.byname.setText(webToonItem.getByname());

            holder.text.setText(webToonItem.getTitle());

            holder.timestamp.setText(webToonItem.getRelease());

        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        try{
            holder.icon.setImageBitmap(webToonItem.getIcon());
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }


        return convertView;
    }

    public ViewHolder connectResource(View convertView,int resource){
            ViewHolder holder=new ViewHolder();


        switch (resource)
        {
            case ITEM_LIST_TYPE:

                holder.byname=convertView.findViewById(R.id.webtoon_byname);
                holder.icon=convertView.findViewById(R.id.Webtoon_Image);
                holder.text=convertView.findViewById(R.id.webtoon_title);
                holder.timestamp=convertView.findViewById(R.id.webtoon_up_time);

                break;

            case ITEM_VIEW_TYPE:


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


