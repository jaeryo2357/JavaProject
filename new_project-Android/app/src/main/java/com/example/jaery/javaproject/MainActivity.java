package com.example.jaery.javaproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    boolean express;
    View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<WebToonItem> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        express=false;// 처음에는 검색창이 확장되어 있지않음


        editText=findViewById(R.id.search_webtoon);
        view=findViewById(R.id.inflate);

        editText.setOnClickListener(new View.OnClickListener(

        ) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                if(express)
                {
                    /*
                    RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)editText.getLayoutParams();
                //    layoutParams.removeRule(RelativeLayout.ALIGN_LEFT);
                    layoutParams.addRule(RelativeLayout.ALIGN_LEFT,R.id.notinflate);
                    editText.setLayoutParams(layoutParams);
                    express=false;
                    editText.setFocusable(false);
                    layoutParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
                    layoutParams.leftMargin=230;
                    view.setLayoutParams(layoutParams);
                    e
*/
                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            editText.setFocusableInTouchMode(true);
                            editText.requestFocus();

                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                            imm.showSoftInput(editText,0);

                        }
                    });

                }
                else{
                    express = true;
                                editText.clearFocus();
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
                                //    layoutParams.removeRule(RelativeLayout.ALIGN_LEFT);
                                layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.inflate);
                                editText.setLayoutParams(layoutParams);

                                layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    ValueAnimator animator = ValueAnimator.ofInt(layoutParams.leftMargin, 0);
                    final RelativeLayout.LayoutParams finalLayoutParams = layoutParams;
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator)
                        {
                            finalLayoutParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();

                            view.setLayoutParams(finalLayoutParams);
                        }
                    });
                    animator.setDuration(200);
                    animator.start();
                    }
                }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)editText.getLayoutParams();
                    //    layoutParams.removeRule(RelativeLayout.ALIGN_LEFT);
                    layoutParams.addRule(RelativeLayout.ALIGN_LEFT,R.id.notinflate);
                    editText.setLayoutParams(layoutParams);
                    express=false;
                    editText.setText("");
                    editText.setFocusable(false);
                    layoutParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
                    layoutParams.leftMargin=230;
                    view.setLayoutParams(layoutParams);
                }
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset = new ArrayList<>();
        mAdapter = new CardViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new WebToonItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.webtoon_test)));
        myDataset.add(new WebToonItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.webtoon_test)));

        myDataset.add(new WebToonItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.webtoon_test)));





/*
     final WebtoonListAdapter adapter=new WebtoonListAdapter(this);

        ///리스트 추가하는 부분
       adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));


        adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가4","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가5","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가6","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));

        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));

        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WebToonItem ClickingItem=adapter.getItem(position);
                Toast.makeText(MainActivity.this,ClickingItem.getTitle(),Toast.LENGTH_LONG).show();
            }
        });



*/

    }
}
