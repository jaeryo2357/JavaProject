package com.example.jaery.javaproject;

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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    TextView t1;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


       // LayoutInflater layoutInflater=getLayoutInflater();

        //LinearLayout toastView=(LinearLayout)layoutInflater.inflate(R.layout.mytoast,null);

        EditText edit=(EditText)findViewById(R.id.EditText);
        EditText edit2=(EditText)findViewById(R.id.et1);

        TextView.OnEditorActionListener a= new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId==EditorInfo.IME_ACTION_GO)
                {
                    Toast.makeText(MainActivity.this,"첫번째",Toast.LENGTH_LONG).show();
                    return true;
                }
                else if(actionId==EditorInfo.IME_ACTION_NEXT) {
                    Toast.makeText(MainActivity.this, "두번째", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        };

        edit.setOnEditorActionListener(a);
        edit2.setOnEditorActionListener(a);


/*
       Toast t= new Toast(this);
       t.setView(toastView);
       t.setGravity(Gravity.CENTER,100,50);
      // t.setText();
       t.setDuration(Toast.LENGTH_LONG);
       t.show();



/*
        b1=(Button)findViewById(R.id.ButtonAdd);
        b2=(Button)findViewById(R.id.ButtonSub);
        b3=(Button)findViewById(R.id.ButtonMux);
        b4=(Button)findViewById(R.id.ButtonDiv);
        t1=(TextView)findViewById(R.id.TextView1);


        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p1,p2;
                String temp;

                try {
                    et1 = (EditText) findViewById(R.id.EditText1);
                    p1 = Integer.parseInt(et1.getText().toString());
                    et1 = (EditText) findViewById(R.id.EditText2);
                    p2 = Integer.parseInt(et1.getText().toString());

                    switch (v.getId()) {
                        case R.id.ButtonAdd:
                            t1.setText("" + (p1 + p2));
                            break;
                        case R.id.ButtonSub:
                            t1.setText("" + (p1 - p2));
                            break;
                        case R.id.ButtonMux:
                            t1.setText("" + (p1 * p2));
                            break;
                        case R.id.ButtonDiv:
                            t1.setText("" + ((double) (p1) / (double) (p2)));
                            break;
                    }
                }catch (NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"숫자를 채워주세요",Toast.LENGTH_LONG).show();
                }
            }
        };

        b1.setOnClickListener(listener);

        b2.setOnClickListener(listener);

        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);
*/
    }
}
