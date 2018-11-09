package com.example.jaery.javaproject;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    boolean express = false;
    View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<WebToonItem> myDataset;
    boolean gone=false;

    int oldScrollY=0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        express = false;// 처음에는 검색창이 확장되어 있지않음


        editText = findViewById(R.id.search_webtoon);
        view = findViewById(R.id.inflate);

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

        mRecyclerView = (RecyclerView)

                findViewById(R.id.recycle);

        mLayoutManager = new

                LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset = new ArrayList<>();
        mAdapter = new

                CardViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new

                WebToonItem(R.layout.webtoon_list_item, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.webtoon_test)));
        myDataset.add(new

                WebToonItem(R.layout.webtoon_list_item, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.webtoon_test)));

        myDataset.add(new

                WebToonItem(R.layout.webtoon_list_item, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.webtoon_test)));

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), position + "번 째 아이템 클릭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
              //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();
                DialogFragment notice=new NoticeDialogFragment();
                notice.setCancelable(false);
                notice.show(getSupportFragmentManager(),"notice");
            }
        }));
/*
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(oldScrollY==0)
                {
                    oldScrollY=dy;
                }else if((oldScrollY-dy)>0)
                {
                    if(gone==false) {
                        RelativeLayout relativeLayout = findViewById(R.id.topbar);
                        relativeLayout.setVisibility(View.GONE);
                        gone = true;
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
                        layoutParams.removeRule(RelativeLayout.BELOW);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                        recyclerView.setLayoutParams(layoutParams);
                    }
                        oldScrollY = dy;

                }else
                {
                    if(gone) {
                        RelativeLayout relativeLayout = findViewById(R.id.topbar);
                        relativeLayout.setVisibility(View.VISIBLE);
                        gone=false;
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
                        layoutParams.addRule(RelativeLayout.BELOW, R.id.topbar);
                        layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                        recyclerView.setLayoutParams(layoutParams);
                    }
                    oldScrollY=dy;
                }
            }
        });
*/

    }

    public static class NoticeDialogFragment extends DialogFragment{  //public 이 2개있을수없어 static으로 만듬
       @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


           AlertDialog.Builder adBuilder= new AlertDialog.Builder(getActivity());
           adBuilder.setMessage("Time out!")
                   .setTitle("Notice")
                   .setIcon(R.mipmap.ic_launcher)
                   .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   })
                   .setCancelable(false);
           return adBuilder.create();
           //return super.onCreateDialog(savedInstanceState);
       }
    }


}
