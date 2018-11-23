package com.example.jaery.javaproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by gdtbg on 2017-09-16.
 */

public class AutoScrollAdapter extends PagerAdapter {

    Context context;
    ArrayList<Bitmap> data;
    int size;

    public AutoScrollAdapter(Context context, ArrayList<Bitmap> data) {
        this.context = context;
        this.data = data;
    }

    interface viewpagerOnClickListener{
        public void OnPageClick(View v,int position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //뷰페이지 슬라이딩 할 레이아웃 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.auto_viewpager,null);
        ImageView image_container = (ImageView) v.findViewById(R.id.image_container);
        image_container.setImageBitmap(data.get(position));
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);

    }

    @Override
    public int getCount() {
        return size;
    }

    public void setData(ArrayList<Bitmap> mData) {
        this.data = mData;
        try{
            notifyDataSetChanged();
            size=data.size();
        }
        catch(Exception e) {
           Log.e("Coupon View", e.toString());
        }
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

