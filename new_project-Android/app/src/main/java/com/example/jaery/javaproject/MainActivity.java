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

        WebtoonListAdapter adapter=new WebtoonListAdapter(this);

        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        adapter.addItem("신과함께","작가1","2018.10.2",BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);




    }
}
