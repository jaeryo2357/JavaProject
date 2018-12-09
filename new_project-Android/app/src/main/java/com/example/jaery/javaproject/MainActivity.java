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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
    SearchFragment searchFr;
    EditText editText;
    boolean express = false;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    View view;
    private boolean isFragmentA=true;
    private boolean isFragmentB=false;
    private boolean isFragmentC=false;
    DBOpenHelper mydb;
    String SearchString="";

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

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_SEARCH) {
                    SearchString=editText.getText().toString();
                    unexpress();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                    FrSearch(null);
                }
                return false;
            }
        });


    }

    public void unexpress()
    {
        TextView textView = findViewById(R.id.Project_title);
        textView.setVisibility(View.VISIBLE);


        express = false;
        editText.setText("");
        editText.setFocusable(false);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

        ValueAnimator animator = ValueAnimator.ofInt(0, 250);
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

        layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
       layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.notinflate);
        editText.setLayoutParams(layoutParams);

    }

   public void FrCotent(View v)
   {
       if(isFragmentA||isFragmentC)
       {    isFragmentB=true;
            isFragmentA=false;
            isFragmentC=false;
            webToonContentFragment=new WebToonContentFragment();
           fm=getSupportFragmentManager();
           fragmentTransaction=fm.beginTransaction();
           if(mainFragment!=null){
           fragmentTransaction.remove(mainFragment);
           mainFragment=null;
           }
           else {
               fragmentTransaction.remove(searchFr);
                searchFr=null;
           }
           fragmentTransaction.add(R.id.main_FrameLayout,webToonContentFragment);
           fragmentTransaction.commit();
           ImageButton b=findViewById(R.id.main_B_home);
          b.setImageResource(R.drawable.ic_person_outline_black_24dp);
           b=findViewById(R.id.main_B_Content);
           b.setImageResource(R.drawable.ic_label_black_24dp);
           b=findViewById(R.id.main_B_Search);
           b.setImageResource(R.drawable.ic_people_outline_black_24dp);

       }
   }
    public void FrSearch(View view)
    {
        if(isFragmentB||isFragmentA)
        {
            isFragmentC=true;
            isFragmentA=false;
            isFragmentB=false;

            fm=getSupportFragmentManager();
            fragmentTransaction=fm.beginTransaction();
            if(webToonContentFragment!=null) {
                fragmentTransaction.remove(webToonContentFragment);
                webToonContentFragment=null;
            }
            else if(mainFragment!=null){
                fragmentTransaction.remove(mainFragment);
                mainFragment=null;
            }else
            {
                fragmentTransaction.remove(searchFr);
                searchFr=null;
            }


            searchFr=new SearchFragment();
            Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
            bundle.putString("searchString", SearchString);
            SearchString="";// key , value
            searchFr.setArguments(bundle);

            fragmentTransaction.replace(R.id.main_FrameLayout,searchFr);
            fragmentTransaction.commit();

            ImageButton b=findViewById(R.id.main_B_home);
            b.setImageResource(R.drawable.ic_person_outline_black_24dp);
            b=findViewById(R.id.main_B_Content);
            b.setImageResource(R.drawable.ic_label_outline_black_24dp);
            b=findViewById(R.id.main_B_Search);
            b.setImageResource(R.drawable.ic_people_black_24dp);
        }
    }
   public void FrHome(View view)
   {
       if(isFragmentB||isFragmentC)
       {
           isFragmentA=true;
           isFragmentB=false;
           isFragmentC=false;
           mainFragment=new MainFragment();
           fm=getSupportFragmentManager();
           fragmentTransaction=fm.beginTransaction();
           if(webToonContentFragment !=null){
           fragmentTransaction.remove(webToonContentFragment);
           webToonContentFragment=null;}
           else {
               fragmentTransaction.remove(searchFr);
               searchFr = null;
           }
           fragmentTransaction.add(R.id.main_FrameLayout,mainFragment);
           fragmentTransaction.commit();

           ImageButton b=findViewById(R.id.main_B_home);
           b.setImageResource(R.drawable.ic_person_black_24dp);
           b=findViewById(R.id.main_B_Content);
          b.setImageResource(R.drawable.ic_label_outline_black_24dp);
          b=findViewById(R.id.main_B_Search);
          b.setImageResource(R.drawable.ic_people_outline_black_24dp);
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
