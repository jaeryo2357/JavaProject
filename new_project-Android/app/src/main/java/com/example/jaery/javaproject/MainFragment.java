package com.example.jaery.javaproject;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainFragment extends Fragment {

    AutoScrollViewPager autoViewPager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<WebToonItem> myDataset;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main,container,false);


        ArrayList<Integer> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add(R.drawable.web1);
        data.add(R.drawable.web2);
        data.add(R.drawable.web3);




        autoViewPager = (AutoScrollViewPager)view.findViewById(R.id.main_viewpager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getActivity(), data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(5000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작

        mRecyclerView = (RecyclerView)
                view.findViewById(R.id.RecomendRecycle);
        mLayoutManager = new
                LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset = new ArrayList<>();
        mAdapter = new
                WebtoonAdapter(myDataset,getActivity(), new WebtoonAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v, int position) {

            }

            @Override
            public void MapOnClick(View v, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new

                WebToonItem(0, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.web1)));
        myDataset.add(new

                WebToonItem(0, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.web2)));

        myDataset.add(new

                WebToonItem(0, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.web3)));

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + "번 째 아이템 클릭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();

            }
        }));

        return view;
    }
}
