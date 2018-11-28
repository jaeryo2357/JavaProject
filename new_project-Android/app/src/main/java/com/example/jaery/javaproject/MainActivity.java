package com.example.jaery.javaproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    WebToonContentFragment webToonContentFragment;
    EditText editText;
    boolean express = false;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    View view;
    private boolean isFragmentA=true;
    private boolean isFragmentB=false;
    DBOpenHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        express = false;// 처음에는 검색창이 확장되어 있지않음

        mydb=new DBOpenHelper(this);
        mydb.open();
        editText = findViewById(R.id.search_webtoon);
        view = findViewById(R.id.inflate);
////// View pager
        mainFragment=new MainFragment();
        fm=getSupportFragmentManager();
        fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.add(R.id.main_FrameLayout,mainFragment);
        fragmentTransaction.commit();


        ///////////////
        editText.setOnClickListener(new View.OnClickListener(

        ) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                if (express) {

                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            editText.setFocusableInTouchMode(true);
                            editText.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(editText, 0);

                        }
                    });

                } else {

                    express = true;
                    TextView textView = findViewById(R.id.Project_title);
                    textView.setVisibility(View.GONE);
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
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            finalLayoutParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();

                            view.setLayoutParams(finalLayoutParams);
                        }
                    });
                    animator.setDuration(300);
                    animator.start();

                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            editText.setFocusableInTouchMode(true);
                            editText.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(editText, 0);

                        }
                    });
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
                    //    layoutParams.removeRule(RelativeLayout.ALIGN_LEFT);
                    layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.notinflate);
                    editText.setLayoutParams(layoutParams);
                    express = false;
                    editText.setText("");
                    editText.setFocusable(false);
                    layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = 250;
                    view.setLayoutParams(layoutParams);
                }
            }
        });


    }


   public void FrCotent(View v)
   {
       if(isFragmentA)
       {    isFragmentB=true;
            isFragmentA=false;
            webToonContentFragment=new WebToonContentFragment();
           fm=getSupportFragmentManager();
           fragmentTransaction=fm.beginTransaction();
           fragmentTransaction.remove(mainFragment);
           fragmentTransaction.add(R.id.main_FrameLayout,webToonContentFragment);
           fragmentTransaction.commit();
           ImageButton b=findViewById(R.id.main_B_home);
          b.setImageResource(R.drawable.ic_person_outline_black_24dp);
           b=findViewById(R.id.main_B_Content);
           b.setImageResource(R.drawable.ic_label_black_24dp);

       }
   }

   public void FrHome(View view)
   {
       if(isFragmentB)
       {
           isFragmentA=true;
           isFragmentB=false;
           mainFragment=new MainFragment();
           fm=getSupportFragmentManager();
           fragmentTransaction=fm.beginTransaction();
           fragmentTransaction.remove(webToonContentFragment);
           fragmentTransaction.add(R.id.main_FrameLayout,mainFragment);
           fragmentTransaction.commit();

           ImageButton b=findViewById(R.id.main_B_home);
           b.setImageResource(R.drawable.ic_person_black_24dp);
           b=findViewById(R.id.main_B_Content);
          b.setImageResource(R.drawable.ic_label_outline_black_24dp);
       }
   }


    public void gotoMypage(View v)
    {
        if(mydb.findauto()==0||mydb.findauto()==-1) {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }else
        {
            //Toast.makeText(MainActivity.this,"로그인 상태",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,MyPage.class);
            intent.putExtra("M_ID",mydb.findID());
            startActivity(intent);
        }
    }
}
