package com.example.jaery.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchFragment extends Fragment {
    TextView tv_member;
    TextView tv_Webtoon;
    RecyclerView recyclerView_member;
    RecyclerView recyclerView_Webtoon;
    ArrayList<WebToonItem> r_m=new ArrayList<>();
    ArrayList<WebToonItem> r_w=new ArrayList<>();
    String searchString;
    WebtoonAdapter M_A;
    WebtoonAdapter W_A;
    TextView tv_m;
    TextView tv_w;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tv_member = view.findViewById(R.id.search_textMember);
        tv_m=view.findViewById(R.id.search_textMemberNum);
        tv_w=view.findViewById(R.id.search_textWebtoonNum);
        searchString = getArguments().getString("searchString");

        if(savedInstanceState !=null)
        {
            tv_w.setText(savedInstanceState.getString("Webtoon"));
            tv_m.setText(savedInstanceState.getString("member"));
            r_w=savedInstanceState.getParcelableArrayList("WebtoonArray");
            r_m=savedInstanceState.getParcelableArrayList("memberArray");
        }

        tv_Webtoon = view.findViewById(R.id.search_textWebtoon);
        recyclerView_member = view.findViewById(R.id.search_RecycleMember);
        recyclerView_Webtoon = view.findViewById(R.id.search_RecycleWebtoon);
        recyclerView_member.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_Webtoon.setLayoutManager(new LinearLayoutManager(getActivity()));
        M_A=new WebtoonAdapter(r_m,getActivity(),null);
        W_A=new WebtoonAdapter(r_w,getActivity(),null);
        recyclerView_member.setAdapter(M_A);
        recyclerView_Webtoon.setAdapter(W_A);

        recyclerView_member.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView_member, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),MyPage.class);
                intent.putExtra("M_ID",r_m.get(position).getBigimage());
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));

        recyclerView_Webtoon.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView_Webtoon, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),WebtoonList.class);
                intent.putExtra("Title",r_w.get(position).getTitle());
                intent.putExtra("Genre",r_w.get(position).getRelease());
                intent.putExtra("Byname",r_w.get(position).getByname());
                intent.putExtra("ID",r_w.get(position).getID());
                intent.putExtra("URL",r_w.get(position).getSmallimage());
                startActivity(intent);
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

        final GetJson json=GetJson.getInstance();

        new Thread() {
            @Override
            public void run() {
                json.requestWebServer(callback, "Search.php", "Search="+searchString);
            }
        }.start();



        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("WebtoonArray", (ArrayList<? extends Parcelable>) r_w);
        outState.putParcelableArrayList("memberArray", (ArrayList<? extends Parcelable>) r_m);
        outState.putString("Webtoon",tv_w.getText().toString());
        outState.putString("member",tv_m.getText().toString());
    }

    private final Callback callback= new Callback() {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("webtoon", "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("webtoon", "서버에서 응답한 Body:" + body);
            Handler handler = new Handler(Looper.getMainLooper());
            try {
                JSONObject json=new JSONObject(body);

                JSONArray jsonArray=new JSONArray(json.getString("UserArray"));

                JSONArray jsonArray2=new JSONArray(json.getString("WebtoonArray"));
                final int memberNum=jsonArray.length();


                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject data=jsonArray.getJSONObject(i);
                    r_m.add(new WebToonItem(3,data.getString("ID_Key"),
                            "",
                            data.getString("Name"),
                            "",
                            "",
                            data.getString("ID"),"",null));
                }


                final int webtoonNum=jsonArray2.length();
                for(int i=0;i<jsonArray2.length();i++)
                {
                    JSONObject data=jsonArray2.getJSONObject(i);

                    r_w.add(new WebToonItem(3,data.getString("W_ID"),
                            "",
                            data.getString("W_Title"),
                            data.getString("W_ByName"),
                            data.getString("W_Genre"),
                            "",data.getString("W_Image"),null));

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        M_A.notifyDataSetChanged();
                        tv_m.setText("("+memberNum+")");
                        tv_w.setText("("+webtoonNum+")");
                        W_A.notifyDataSetChanged();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };



}
