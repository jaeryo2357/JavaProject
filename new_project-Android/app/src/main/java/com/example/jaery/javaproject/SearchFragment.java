package com.example.jaery.javaproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    TextView tv_member;
    TextView tv_Webtoon;
    RecyclerView recyclerView_member;
    RecyclerView recyclerView_Webtoon;
    ArrayList<WebToonItem> r_m;
    ArrayList<WebToonItem> r_w;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tv_member = view.findViewById(R.id.search_textMember);

        tv_Webtoon = view.findViewById(R.id.search_textWebtoon);
        recyclerView_member = view.findViewById(R.id.search_RecycleMember);
        recyclerView_Webtoon = view.findViewById(R.id.search_RecycleWebtoon);
        recyclerView_member.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_Webtoon.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_member.setAdapter(new WebtoonAdapter(r_m,getActivity(),null));
        recyclerView_Webtoon.setAdapter(new WebtoonAdapter(r_w,getActivity(),null));

        recyclerView_member.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView_member, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + "번 째 아이템 클릭", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));

        recyclerView_Webtoon.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView_Webtoon, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + "번 째 아이템 클릭", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));

        View.OnClickListener tvonClick = new View.OnClickListener() {
            public void onClick(View v) {
                RecyclerView rv = null;
                switch (v.getId()) {
                    case R.id.search_textMember:
                        rv = recyclerView_member;
                        break;
                    case R.id.search_textWebtoon:
                        rv = recyclerView_Webtoon;
                        break;
                }
                try {
                    if (rv.getVisibility() == View.VISIBLE) {
                        rv.setVisibility(View.GONE);
                    } else {
                        rv.setVisibility(View.VISIBLE);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };
        tv_member.setOnClickListener(tvonClick);
        tv_Webtoon.setOnClickListener(tvonClick);
        return view;
    }


}
