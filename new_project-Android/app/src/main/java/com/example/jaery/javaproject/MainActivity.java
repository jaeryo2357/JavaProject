package com.example.jaery.javaproject;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final WebtoonListAdapter adapter=new WebtoonListAdapter(this);



        ///리스트 추가하는 부분
        adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));


        adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));

        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));

        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food)); adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));

        adapter.addItem(R.layout.webtoon_view_item,BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));
        adapter.addItem(R.layout.webtoon_list_item,"신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.drawable.cat_food));

        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WebToonItem ClickingItem=adapter.getItem(position);
                Toast.makeText(MainActivity.this,ClickingItem.getTitle(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
