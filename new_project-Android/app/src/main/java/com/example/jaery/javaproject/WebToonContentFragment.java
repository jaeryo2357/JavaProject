package com.example.jaery.javaproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WebToonContentFragment extends Fragment {

    private ArrayList<String> genre;
    private ArrayList<WebToonItem> webtoon;
    private RecyclerView mRecyclerView_genre;
    private RecyclerView mRecyclerView_Webtoon;
    private WebtoonAdapter mAdapter_genre;
    private WebtoonAdapter mAdapter_Webtoon;
    private RecyclerView.LayoutManager mLayoutManager_genre;
    private RecyclerView.LayoutManager mLayoutManager_Webtoon;
    ProgressDialog loading;
    String[] genre_string;
    int position;
    GetJson json;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_webtoon,container,false);
        webtoon=new ArrayList<>();
        position=0;
        json=GetJson.getInstance();
        genre_string=new String[]{"드라마","일상","스릴러","액션","SF","스포츠","슬픔"};
        genre=new ArrayList<>();
        genre.addAll(Arrays.asList(genre_string));
        mRecyclerView_genre=view.findViewById(R.id.Genre_recycle);
        mRecyclerView_Webtoon=view.findViewById(R.id.Webtoon_recycle);
        mAdapter_genre= new WebtoonAdapter(genre,getActivity(),new WebtoonAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v, int position) {
            }
            @Override
            public void MapOnClick(View v, int position) {
            }
        },new boolean[]{true,false,false,false,false,false,false});
        mLayoutManager_genre = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) mLayoutManager_genre).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_Webtoon=new GridLayoutManager(getActivity(),2);
        mRecyclerView_Webtoon.offsetChildrenVertical(2);
        mRecyclerView_Webtoon.offsetChildrenHorizontal(2);
        mRecyclerView_genre.setLayoutManager(mLayoutManager_genre);
        mRecyclerView_Webtoon.setLayoutManager(mLayoutManager_Webtoon);
        mAdapter_Webtoon=new WebtoonAdapter(webtoon,getActivity(),null);
        mRecyclerView_Webtoon.setAdapter(mAdapter_Webtoon);
        mRecyclerView_genre.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView_genre, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                boolean[] check=new boolean[]{false,false,false,false,false,false,false};
                check[position]=true;
                mAdapter_genre=new WebtoonAdapter(Arrays.asList(genre_string),getActivity(),null,check);
                mRecyclerView_genre.setAdapter(mAdapter_genre);
                webtoon.clear();
                mAdapter_Webtoon.notifyDataSetChanged();
                loading=ProgressDialog.show(getActivity(), "Wait...", null, true, true);
                new Thread()
                {
                    @Override
                    public void run() {
                        json.requestWebServer(callback,"AllWebtoon.php","ID="+genre_string[position]);
                    }
                }.start();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));
        mRecyclerView_genre.setAdapter(mAdapter_genre);
        loading=ProgressDialog.show(getActivity(), "Wait...", null, true, true);
        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"AllWebtoon.php","ID="+genre_string[position]);
            }
        }.start();

        mRecyclerView_Webtoon.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView_Webtoon, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),WebtoonList.class);
                intent.putExtra("Title",webtoon.get(position).getTitle());
                intent.putExtra("Genre",webtoon.get(position).getGenre());
                intent.putExtra("Byname",webtoon.get(position).getByname());
                intent.putExtra("ID",webtoon.get(position).getID());
                intent.putExtra("URL",webtoon.get(position).getSmallimage());
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));

        return view;
    }

    private final Callback callback = new Callback() {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("webtoon", "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
           String body = response.body().string();
            Log.d("webtoon", "서버에서 응답한 Body:" + body);

            Handler handler = new Handler(Looper.getMainLooper());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (loading!=null)
                    loading.dismiss();
                }
            });

            try {
                JSONArray dataarr=new JSONArray(body);

                for(int i=0;i<dataarr.length();i++) {
                    JSONObject data=dataarr.getJSONObject(i);

                    URL url = new URL(data.getString("small_image").replace("\\", ""));

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();

                    webtoon.add(new WebToonItem(2,data.getString("ID"), data.getString("Genre"), data.getString("Title"),
                            data.getString("ByName"), "",data.getString("big_image"),data.getString("small_image"), BitmapFactory.decodeStream(is)));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter_Webtoon.notifyDataSetChanged();
                        }
                    });

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


}


