package com.example.jaery.javaproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserList extends AppCompatActivity {

    GetJson json;
    RecyclerView.Adapter recycleviewAdatper;
    ArrayList<WebToonItem> arrayList;

    @Override
    protected void onRestart() {
        super.onRestart();

        arrayList.clear();
        if (!php.equals("Wish")) {
            new Thread() {
                @Override
                public void run() {
                    json.requestWebServer(callback, "FollowSearch.php", "ID=" + mynum, "php=" + php);
                }
            }.start();
        } else {
            new Thread() {
                @Override
                public void run() {
                    json.requestWebServer(callback2, "WishSearch.php", "ID=" + mynum);
                }
            }.start();
        }
    }
    String php;
    String mynum;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        json=GetJson.getInstance();
        final Intent intent=getIntent();
        mynum=intent.getStringExtra("ID");
        php=intent.getStringExtra("php");
        RecyclerView recyclerView=findViewById(R.id.userlist_recycle);
        recyclerView.setLayoutManager(new GridLayoutManager(UserList.this,2));

        recyclerView.offsetChildrenHorizontal(2);
        recyclerView.offsetChildrenVertical(2);


        arrayList=new ArrayList<>();
        recycleviewAdatper=new WebtoonAdapter(arrayList,UserList.this,null);
        recyclerView.setAdapter(recycleviewAdatper);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(UserList.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                Intent intent1;
                if(!php.equals("Wish"))
                {
                    intent1=new Intent(UserList.this,MyPage.class);
                    intent1.putExtra("M_ID",arrayList.get(position).getGenre());

                }else
                {
                    intent1=new Intent(UserList.this,WebtoonList.class);
                    intent1.putExtra("Title",arrayList.get(position).getTitle());
                    intent1.putExtra("Genre",arrayList.get(position).getGenre());
                    intent1.putExtra("Byname",arrayList.get(position).getByname());
                    intent1.putExtra("ID",arrayList.get(position).getID());
                    intent1.putExtra("URL",arrayList.get(position).getSmallimage());
                    intent1.putExtra("Explan",arrayList.get(position).getRelease());
                }

                startActivity(intent1);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
            }
        }));
        if(!php.equals("Wish")) {
            new Thread() {
                @Override
                public void run() {
                    json.requestWebServer(callback, "FollowSearch.php", "ID=" + mynum, "php=" + php);
                }
            }.start();
        }else{
            new Thread() {
                @Override
                public void run() {
                    json.requestWebServer(callback2, "WishSearch.php", "ID=" + mynum);
                }
            }.start();
        }
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


            try {
                JSONArray dataarr=new JSONArray(body);

                for(int i=0;i<dataarr.length();i++) {
                    JSONObject data=dataarr.getJSONObject(i);



                    arrayList.add(new WebToonItem(2,data.getString("U_ID_Key"),data.getString("U_ID"), data.getString("U_name"),
                            "", "","","", null));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            recycleviewAdatper.notifyDataSetChanged();
                        }
                    });

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
                JSONArray dataarr=new JSONArray(body);

                for(int i=0;i<dataarr.length();i++) {
                    JSONObject data=dataarr.getJSONObject(i);



                    URL url = new URL(data.getString("small_image").replace("\\", ""));

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();

                    arrayList.add(new WebToonItem(2,data.getString("ID"), data.getString("Genre"), data.getString("Title"),
                            data.getString("ByName"), data.getString("Explan"),data.getString("big_image"),data.getString("small_image"), BitmapFactory.decodeStream(is)));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            recycleviewAdatper.notifyDataSetChanged();
                        }
                    });

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
