package kr.ac.kpu.Eureka.Main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.ac.kpu.Eureka.Parser.JsonParser;
import kr.ac.kpu.Eureka.R;

/**
 * Created by namjung on 2017. 5. 23..
 */

public class SignupActivity extends AppCompatActivity {
    EditText id,password,name,email,phone;
    Button okbtn,cancelbtn;
    public static Handler handler , handler2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        id = (EditText)findViewById(R.id.id);
        password = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        okbtn = (Button)findViewById(R.id.ok);
        cancelbtn = (Button)findViewById(R.id.cancel);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(),"오류가 발생하였습니다",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                finish();
                Toast.makeText(getApplicationContext(),"계정이 생성되었습니다.",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ids = id.getText().toString();
                String passwd = password.getText().toString();
                String phoneNumber = phone.getText().toString();
                String names = name.getText().toString();
                String emails = email.getText().toString();
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().CreateUser(ids,passwd,phoneNumber,names,emails),1);
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
