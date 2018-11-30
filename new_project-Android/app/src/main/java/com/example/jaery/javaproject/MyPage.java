package com.example.jaery.javaproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyPage extends AppCompatActivity {

    GetJson json;
    Intent intent;
    TextView tv_name;
    Button b_name;
    TextView tv_wish_num;
    String my_num=null;
    DBOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        b_name=findViewById(R.id.page_buttn);
        intent=getIntent();
        json=GetJson.getInstance();
        db=new DBOpenHelper(this);
        db.open();
        tv_name=findViewById(R.id.page_name);
        tv_wish_num=findViewById(R.id.page_wish_num);
        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"SelectUser.php","ID="+intent.getStringExtra("M_ID"));
            }
        }.start();
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
                JSONObject data=new JSONObject(body);


                final String check=data.getString("NAME");


                my_num=data.getString("ID_Key");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(db.findID().equals(check))
                            b_name.setText("로그 아웃");
                        else
                        {
                            b_name.setText("Follow");
                        }
                    }
                });
               final String s1=data.getString("Wish_Num");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_name.setText(check);
                     tv_wish_num.setText(s1);
                    }
                });

                JSONArray dataarr=data.getJSONArray("WishArray");

                for(int i=0;i<dataarr.length();i++) {
                    JSONObject data2=dataarr.getJSONObject(i);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
