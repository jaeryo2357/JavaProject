package com.example.jaery.javaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    }
}
