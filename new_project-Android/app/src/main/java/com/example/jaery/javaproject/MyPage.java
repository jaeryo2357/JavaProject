package com.example.jaery.javaproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyPage extends AppCompatActivity {

    GetJson json;
    Intent intent;
    TextView tv_name;
    Button b_name;
    TextView tv_wish_num;
    TextView tv_follow;
    TextView tv_following;
    String my_num=null;
    PieChart pieChart;
    DBOpenHelper db;
    ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

    @Override
    protected void onRestart() {
        super.onRestart();
        yValues.clear();
        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"SelectUser.php","ID="+intent.getStringExtra("M_ID"));
            }
        }.start();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        pieChart = (PieChart)findViewById(R.id.piechart);
        b_name=findViewById(R.id.page_buttn);
        intent=getIntent();
        json=GetJson.getInstance();
        db=new DBOpenHelper(this);
        db.open();
        tv_follow=findViewById(R.id.page_follow_num);
        tv_following=findViewById(R.id.page_follower_num);


        new Thread()
        {
            @Override
            public void run() {
                json.requestWebServer(callback,"SelectUser.php","ID="+intent.getStringExtra("M_ID"));
            }
        }.start();


        tv_name=findViewById(R.id.page_name);
        tv_wish_num=findViewById(R.id.page_wish_num);


        if(db.findauto()!=0&&db.findID().equals(intent.getStringExtra("M_ID")))
            b_name.setText("로그 아웃");
        else if(db.findauto()!=0)
        {
            new Thread() {
                @Override
                public void run() {
                    json.requestWebServer(callback5, "FollowCheck.php", "M_ID=" + db.findID(), "A_ID=" + intent.getStringExtra("M_ID"));
                }
            }.start();
        }
        b_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Button)v).getText().toString().equals("로그 아웃"))
                {
                    if(db.UpdateAuto(0))
                        finish();
                }else if(((Button)v).getText().toString().equals("Follow"))
                {
                    if(db.findauto()==0) {
                        AlertDialog.Builder a = new AlertDialog.Builder(MyPage.this);
                        a.setTitle("주의").setMessage("로그인이 필요한 서비스 입니다.")
                                .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MyPage.this, Register.class);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).show();
                    }else {

                        new Thread() {
                            @Override
                            public void run() {

                                json.requestWebServer(callback2, "Following.php", "M_ID=" + db.findID(), "A_ID=" + my_num);
                            }
                        }.start();
                    }
                }else {
                    if (db.findauto() == 0) {
                        AlertDialog.Builder a = new AlertDialog.Builder(MyPage.this);
                        a.setTitle("주의").setMessage("로그인이 필요한 서비스 입니다.")
                                .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MyPage.this, Register.class);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).show();
                    } else {

                        new Thread() {
                            @Override
                            public void run() {
                                json.requestWebServer(callback3, "UnFollowing.php", "M_ID=" + db.findID(), "A_ID=" + my_num);
                            }
                        }.start();
                    }
                }
            }
        });

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                switch (v.getId())
                {
                    case R.id.page_follow_num:
                        intent=new Intent(MyPage.this,UserList.class);
                        intent.putExtra("php","Follower");
                        intent.putExtra("ID",my_num);
                        startActivity(intent);
                        break;
                    case R.id.page_follower_num:
                        intent=new Intent(MyPage.this,UserList.class);
                        intent.putExtra("php","Following");
                        intent.putExtra("ID",my_num);
                        startActivity(intent);
                        break;
                    case R.id.page_wish_num:
                        intent=new Intent(MyPage.this,UserList.class);
                        intent.putExtra("php","Wish");
                        intent.putExtra("ID",my_num);
                        startActivity(intent);
                        break;
                }
            }
        };

        tv_following.setOnClickListener(onClickListener);
        tv_follow.setOnClickListener(onClickListener);
        tv_wish_num.setOnClickListener(onClickListener);



    }
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

                JSONObject data=new JSONObject(body);
                if(data.getString("result").equals("true"))
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((Button)findViewById(R.id.page_buttn)).setText("UnFollow");
                        }
                    });
                    tv_following.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    int n=Integer.parseInt(tv_following.getText().toString());

                                    if(n!=0)
                                    tv_following.setText(new Integer(n+1).toString());
                                    else
                                        tv_following.setText(new Integer(n).toString());
                                }
                            }
                    );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
    private final Callback callback5 = new Callback() {

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
                if(data.getString("result").equals("true"))
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((Button)findViewById(R.id.page_buttn)).setText("UnFollow");
                        }
                    });
                }else
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((Button)findViewById(R.id.page_buttn)).setText("Follow");
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

                JSONObject data=new JSONObject(body);
                if(data.getString("result").equals("true"))
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((Button)findViewById(R.id.page_buttn)).setText("Follow");
                            int n=Integer.parseInt(tv_following.getText().toString());

                            if(n!=0)
                                tv_following.setText(new Integer(n-1).toString());
                            else
                                tv_following.setText(new Integer(n).toString());
                        }
                    });
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



            try {
                JSONObject data=new JSONObject(body);


                final String check=data.getString("NAME");


                my_num=data.getString("ID_Key");


               final String s1=data.getString("Wish_Num");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_name.setText(check);
                     tv_wish_num.setText(s1);
                    }
                });
                final String s2=data.getString("Follow");
                final String s3=data.getString("Follower");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_follow.setText(s2);
                        tv_following.setText(s3);
                    }
                });
                JSONArray dataarr= new JSONArray(data.getString("WishArray"));

                HashMap<String,Integer> map=new HashMap<>();
                for(int i=0;i<dataarr.length();i++) {

                    JSONObject data2=dataarr.getJSONObject(i);

                    Integer Count=map.get(data2.getString("GENRE"));
                    if(Count==null)Count=1;
                    else Count++;
                    map.put(data2.getString("GENRE"),Count);
                }

                for(String key:map.keySet())
                {
                    yValues.add(new PieEntry(map.get(key),key));
                }

                if(dataarr.length()==0)
                    yValues.add((new PieEntry(1,"선호 없음 ")));


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션
                        pieChart.setUsePercentValues(true);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setExtraOffsets(5,10,5,5);

                        pieChart.setDragDecelerationFrictionCoef(0.95f);

                        pieChart.setDrawHoleEnabled(false);
                        pieChart.setHoleColor(Color.WHITE);
                        pieChart.setTransparentCircleRadius(61f);

                        Description description = new Description();
                        description.setText("선호 장르"); //라벨
                        description.setTextSize(15);
                        pieChart.setDescription(description);
                        PieDataSet dataSet = new PieDataSet(yValues,"Genre");
                        dataSet.setSliceSpace(3f);
                        dataSet.setSelectionShift(5f);
                        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                        PieData data2 = new PieData((dataSet));
                        data2.setValueTextSize(10f);
                        data2.setValueTextColor(Color.YELLOW);
                        pieChart.setData(data2);
                        pieChart.invalidate();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
