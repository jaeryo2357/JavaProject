package com.example.jaery.javaproject;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    int auto=1;
    DBOpenHelper mydb;
    GetJson js;
    EditText editText_id;
    EditText editText_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        js=GetJson.getInstance();
        Button button=findViewById(R.id.register_login);
        Button button_register=findViewById(R.id.register_Newregister);
        editText_id = findViewById(R.id.register_ID);
       editText_pwd = findViewById(R.id.register_pwd);
        mydb=new DBOpenHelper(this);
        mydb.open();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String ID = editText_id.getText().toString();
                final String pwd = editText_pwd.getText().toString();

                if (ID.equals("") || pwd.equals("")) {

                    Toast toast= Toast.makeText(Register.this,"모든 항목을\n입력해 주세요",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,70);
                    toast.show();

                } else {   // 빈칸 없이 입력 url 통신해야함 AlertDialog는 로그인 성공시
                    new Thread() {
                        public void run() {
                            // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                            js=GetJson.getInstance();
                            js.requestWebServer( callback,"login.php","ID="+editText_id.getText().toString(),
                                    "PWD="+editText_pwd.getText().toString());

                        }
                    }.start();
                }
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,NewRegister.class);
                startActivity(intent);
            }
        });

    }


    private final Callback callback = new Callback() {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("nr", "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("nr", "서버에서 응답한 Body:" + body);

            Handler handler = new Handler(Looper.getMainLooper());
            try {
                JSONObject data=new JSONObject(body);
                if(data.getString("result").equals("true")) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
                            alert.setTitle("Auto")
                                    .setMessage("자동 로그인을 하시겠습니까?")
                                    .setPositiveButton("사용", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            auto = 2;
                                            mydb.insert(auto,editText_id.getText().toString(),editText_pwd.getText().toString());
                                            finish();
                                        }
                                    })
                                    .setNegativeButton("다음에", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            auto = 1;
                                            mydb.insert(auto,editText_id.getText().toString(),editText_pwd.getText().toString());
                                            finish();
                                        }
                                    })
                                    .setCancelable(false)
                                    .show();


                        }
                    });



                }else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}
