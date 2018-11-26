package com.example.jaery.javaproject;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainFragment extends Fragment {

    AutoScrollViewPager autoViewPager;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView_New;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter_New;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager_New;
    private ArrayList<WebToonItem> myDataset;
    private ArrayList<WebToonItem> myDataset_New;
    ArrayList<Bitmap> MainNews;
    private ProgressDialog loading;
    Bitmap b3;
    Bitmap b2;
    Bitmap b;
    GetJson json;
    AutoScrollAdapter scrollAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=BitmapFactory.decodeResource(getResources(), R.drawable.web1);
        b2=BitmapFactory.decodeResource(getResources(), R.drawable.web2);
        b3=BitmapFactory.decodeResource(getResources(), R.drawable.web3);
        json=GetJson.getInstance();
    }

    @Override
    public void onDestroyView() {

        if(b.isRecycled()) {
            b.recycle();
            b=null;
        }
        if(b2.isRecycled()) {
            b2.recycle();
            b2=null;
        }
        if(b3.isRecycled()) {
            b3.recycle();
            b3=null;
        }
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);


        MainNews = new ArrayList<>();//이미지 url를 저장하는 arraylist




        autoViewPager = (AutoScrollViewPager) view.findViewById(R.id.main_viewpager);
        scrollAdapter = new AutoScrollAdapter(getActivity(), MainNews);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(5000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작
        loading=ProgressDialog.show(getActivity(), "Wait...", null, true, true);
        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"mainNews.php");
            }
        }.start();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.RecomendRecycle);
        mRecyclerView_New=view.findViewById(R.id.NewWRecycle);
        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_New = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView_New.setLayoutManager(mLayoutManager_New);
        myDataset = new ArrayList<>();
        myDataset_New=new ArrayList<>();
        mAdapter = new
                WebtoonAdapter(myDataset, getActivity(), new WebtoonAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v, int position) {
            }
            @Override
            public void MapOnClick(View v, int position) {
            }
        });

        mAdapter_New = new
                WebtoonAdapter(myDataset_New, getActivity(), new WebtoonAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v, int position) {
            }
            @Override
            public void MapOnClick(View v, int position) {
            }
        });

        mRecyclerView.setAdapter(mAdapter);


        myDataset.add(new WebToonItem(2,"0","", "신과함께1", "작가1","2018.10.2","","",b));
        myDataset.add(new WebToonItem(2,"0","", "신과함께1", "작가1","2018.10.2","","",b2));
        myDataset.add(new WebToonItem(2,"0","", "신과함께1", "작가1","2018.10.2","","",b3));



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
        mRecyclerView_New.setAdapter(mAdapter_New);

      myDataset_New.add(new WebToonItem(0,"0","", "신과함께1", "작가1","2018.10.2","","",b));
        myDataset_New.add(new WebToonItem(0,"0","", "신과함께1", "작가1","2018.10.2","","",b2));
        myDataset_New.add(new WebToonItem(0,"0","", "신과함께1", "작가1","2018.10.2","","",b3));

        mRecyclerView_New.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView_New, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + "번" +
                        "" +
                        " 째 아이템 클릭", Toast.LENGTH_SHORT).show();
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

                    URL url = new URL(data.getString("image").replace("\\", ""));

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();

                    MainNews.add(BitmapFactory.decodeStream(is));


                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollAdapter.setData(MainNews);
                        scrollAdapter.notifyDataSetChanged();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
