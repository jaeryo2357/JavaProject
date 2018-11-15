package com.example.jaery.javaproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    int auto=1;
    DBOpenHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button=findViewById(R.id.register_login);
        mydb=new DBOpenHelper(this);
        mydb.open();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText_id = findViewById(R.id.register_ID);
                EditText editText_pwd = findViewById(R.id.register_pwd);

                final String ID = editText_id.getText().toString();
                final String pwd = editText_pwd.getText().toString();

                if (ID.equals("") || pwd.equals("")) {

                    Toast toast= Toast.makeText(Register.this,"모든 항목을\n입력해 주세요",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,70);
                    toast.show();

                } else {   // 빈칸 없이 입력 url 통신해야함 AlertDialog는 로그인 성공시

                    AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
                    alert.setTitle("Auto")
                            .setMessage("자동 로그인을 하시겠습니까?")
                            .setPositiveButton("사용", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    auto = 2;
                                    mydb.insert(auto,ID,pwd);
                                }
                            })
                            .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    auto = 1;
                                    mydb.insert(auto,ID,pwd);
                                }
                            })
                            .setCancelable(false)
                            .show();
                }
            }
        });

    }




}
