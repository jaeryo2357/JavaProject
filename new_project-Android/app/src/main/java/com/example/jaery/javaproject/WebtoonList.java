package com.example.jaery.javaproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
    Intent intent;
    boolean favorie;
    DBOpenHelper mdb;
    ImageButton favorite_b;

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
        intent=getIntent();
        mdb=new DBOpenHelper(this);
        mdb.open();
        favorite_b=findViewById(R.id.List_favorite);
        ((TextView)findViewById(R.id.List_Title)).setText(intent.getStringExtra("Title"));
        ((TextView)findViewById(R.id.List_Genre)).setText(intent.getStringExtra("Genre"));
        ((TextView)findViewById(R.id.List_Byname)).setText(intent.getStringExtra("Byname"));
        image_small_url=intent.getStringExtra("URL");

        mRecyclerView_Webtoon.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView_Webtoon, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(WebtoonList.this,WebttonViewer.class);
                intent.putExtra("Index",webtoon.get(position).getGenre());
                intent.putExtra("ID",webtoon.get(position).getID());
                intent.putExtra("Content_ID",webtoon.get(position).getTitle().replace("화",""));
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));

        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"WebtoonList.php","ID="+intent.getStringExtra("ID"));
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                json.requestWebServer(callback4, "CheckWish.php", "ID=" + mdb.findID(), "W_ID=" + intent.getStringExtra("ID"));
            }
        }.start();
    }

    public void Fevorite(View v)
    {
        if(mdb.findauto()==0||mdb.findauto()==-1)
        {
            AlertDialog.Builder a=new AlertDialog.Builder(this);
            a.setTitle("주의").setMessage("로그인이 필요한 서비스 입니다.")
                    .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(WebtoonList.this,Register.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setCancelable(false).show();
        }else {

            if (favorie == false) {
                new Thread() {
                    @Override
                    public void run() {
                        json.requestWebServer(callback2, "Wish.php", "ID=" + mdb.findID(), "W_ID=" + intent.getStringExtra("ID"));
                    }
                }.start();
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        json.requestWebServer(callback3, "DelelteWish.php", "ID=" + mdb.findID(), "W_ID=" + intent.getStringExtra("ID"));
                    }
                }.start();
            }
        }

    }



    public void List_back(View v)
    {
        finish();
    }
    private final Callback callback4= new Callback() {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("webtoon", "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("webtoon", "서버에서 응답한 Body:" + body);

            try {
                JSONObject json=new JSONObject(body);
                Handler handler = new Handler(Looper.getMainLooper());
                if(json.getString("result").equals("true")) {
                    favorie=true;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            favorite_b.setImageResource(R.drawable.ic_star_black_24dp);
                        }
                    });
                }else {
                    favorie = false;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            favorite_b.setImageResource(R.drawable.ic_star_border_black_24dp);
                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private final Callback callback3 = new Callback() {

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

                if(json.getString("result").equals("true")) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            favorite_b.setImageResource(R.drawable.ic_star_border_black_24dp);
                        }
                    });

                    favorie=false;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private final Callback callback2 = new Callback() {

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

                if(json.getString("result").equals("true")) {


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            favorite_b.setImageResource(R.drawable.ic_star_black_24dp);
                        }
                    });

                    favorie=true;
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

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

                    webtoon.add(new WebToonItem(3,data.getString("W_ID"),
                            data.getString("last"),
                            data.getString("ID")+"화",
                            data.getString("title"),
                            data.getString("Release"),
                            "","",null));


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
