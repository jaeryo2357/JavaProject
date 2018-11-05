package com.example.jaery.javaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class WebttonViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtton_viewer);

        ListView WebtoonviewList=findViewById(R.id.Webtoon_view_list);

        WebtoonListAdapter adapter=new WebtoonListAdapter(this);




        /* 서버에서 이미지 가져오기*/

        WebtoonviewList.setAdapter(adapter);
    }
}
