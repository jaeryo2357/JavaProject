package com.example.jaery.javaproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WebttonViewer extends AppCompatActivity {

    private RecyclerView mRecycle;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<WebToonItem> webtoon;
    private int lastIndex;
    private String ID;
    private String Content_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtton_viewer);

        webtoon=new ArrayList<>();
        mRecycle=findViewById(R.id.Viewer_Image);
        mAdapter=new WebtoonAdapter(webtoon,this,null);
        mRecycle.setAdapter(mAdapter);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));


        Intent intent=getIntent();
        lastIndex=Integer.parseInt(intent.getStringExtra("Index"));
        ID=intent.getStringExtra("ID");

        Content_ID=intent.getStringExtra("Content_ID");
        new Thread(){
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());

                for(int i=0;i<=lastIndex;i++)
                    {
                        try {
                        URL url = new URL("http://106.10.58.28/Webtoon"+ID+"/"+Content_ID+"/"+i+".jpg");

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);

                        conn.connect();
                        InputStream is = conn.getInputStream();

                        webtoon.add(new WebToonItem(4,"","","","","","","",BitmapFactory.decodeStream(is))) ;



                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }




                    }
            }
        }.start();
    }
}
