package com.example.jaery.javaproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final WebtoonListAdapter adapter=new WebtoonListAdapter(this);



        ///리스트 추가하는 부분
        adapter.addItem("신과함께1","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께2","작가2","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께3","작가3","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께4","작가4","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께5","작가5","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께6","작가6","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께7","작가7","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께8","작가8","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께9","작가9","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
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
