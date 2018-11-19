package com.example.jaery.javaproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewRegister extends AppCompatActivity {

    TextView re_err;
    EditText id;
    EditText name;
    EditText pwd;
    EditText pwd2;
    Button b;
    DBOpenHelper db;
    GetJson js;
    ProgressDialog loading;
    Boolean nameCheck=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

         re_err=findViewById(R.id.Newregister_pwderror);
         id=findViewById(R.id.Newregister_ID);
        name=findViewById(R.id.Newregister_Name);
       pwd=findViewById(R.id.Newregister_pwd);
        pwd2=findViewById(R.id.Newregister_pwd2);
         b=findViewById(R.id.Newregister_login);
        db=new DBOpenHelper(this);
        db.open();
        final Callback callback = new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("nr", "콜백오류:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d("nr", "서버에서 응답한 Body:" + body);
                if(loading!=null)
                    loading.dismiss();
            }
        };
        pwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // 입력되는 텍스트에 변화가 있을 때
                if(pwd.getText().toString().equals(s))
                    re_err.setVisibility(View.GONE);
                else
                    re_err.setVisibility(View.VISIBLE);

            }
            @Override
            public void afterTextChanged(Editable arg0) {

                // 입력이 끝났을 때
                // 입력되는 텍스트에 변화가 있을 때
                if(pwd.getText().toString().equals(arg0.toString()))
                    re_err.setVisibility(View.GONE);
                else
                    re_err.setVisibility(View.VISIBLE);

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // 입력하기 전에

            }

        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1,p2;
                p1=pwd.getText().toString();
                p2=pwd2.getText().toString();

                if(nameCheck==null)
                {
                    Toast.makeText(NewRegister.this,"name 중복체크 해주세요",Toast.LENGTH_LONG).show();
                    return;
                }else if(!nameCheck.booleanValue())
                {
                    Toast.makeText(NewRegister.this,"name이 중복입니다.",Toast.LENGTH_LONG).show();
                    return;
                }

                if(id.getText().toString().equals("")&&pwd.getText().toString().equals("")&&pwd2.getText().toString().equals(""))
                {
                    Toast.makeText(NewRegister.this,"모두 입력해주세요.",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (p1.equals(p2)) {
                        new Thread() {
                            public void run() {
                            // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                                js.requestWebServer( callback,"NewRegister.php","ID="+id.getText().toString(),
                                        "PWD="+pwd.getText().toString(),"NAME="+name.getText().toString());
                                loading = ProgressDialog.show(NewRegister.this, "Register...", null, true, true);
                            }
                        }.start();

                    } else {
                        Toast.makeText(NewRegister.this, "비밀번호 2차를 다시 입력해주세요", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }


}
