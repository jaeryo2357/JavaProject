package com.example.jaery.javaproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

// 웹툰 화를 보여주는 목록 Activity
public class WebtoonList extends AppCompatActivity {


    private ArrayList<WebToonItem> webtoon;
    private RecyclerView mRecyclerView_Webtoon;
    private WebtoonAdapter mAdapter_Webtoon;
    private String image_small_url;
    private ImageView imageView;
    GetJson json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoon_list);
        json=GetJson.getInstance();
        webtoon=new ArrayList<>();
        mRecyclerView_Webtoon=findViewById(R.id.List_Recycle);
        mRecyclerView_Webtoon.setLayoutManager(new LinearLayoutManager(this));
        mAdapter_Webtoon=new WebtoonAdapter(webtoon,this,null);
        mRecyclerView_Webtoon.setAdapter(mAdapter_Webtoon);
        imageView=findViewById(R.id.List_Image);
        final Intent intent=getIntent();

        ((TextView)findViewById(R.id.List_Title)).setText(intent.getStringExtra("Title"));
        ((TextView)findViewById(R.id.List_Genre)).setText(intent.getStringExtra("Genre"));
        ((TextView)findViewById(R.id.List_Byname)).setText(intent.getStringExtra("Byname"));
        image_small_url=intent.getStringExtra("URL");


        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"WebtoonList.php","ID="+intent.getStringExtra("ID"));
            }
        }.start();

    }
    public void List_back(View v)
    {
        finish();
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

            URL url = new URL(image_small_url.replace("\\",""));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();



            InputStream is = conn.getInputStream();
            final Bitmap b=BitmapFactory.decodeStream(is);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(b);
                }
            });
            try {
                JSONArray dataarr=new JSONArray(body);

                for(int i=0;i<dataarr.length();i++) {
                    JSONObject data=dataarr.getJSONObject(i);




                    webtoon.add(new WebToonItem(3,data.getString("last"),"", data.getString("ID")+"화",
                            data.getString("title"), data.getString("Release"),"","",null));


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
