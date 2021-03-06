package kr.ac.kpu.Eureka.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kr.ac.kpu.Eureka.Data.MyInfo;
import kr.ac.kpu.Eureka.Parser.JsonParser;
import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.Tab.Tabbar;

public class MainActivity extends AppCompatActivity{
    EditText id,password;
    public static Handler handler, login_handler;
    public static Handler handler2;

    EditText editText1 ;
    EditText editText2 ;
    String abc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonParser.SetConetxt(this);
        editText1 = (EditText) findViewById(R.id.idEdit);
        editText2= (EditText) findViewById(R.id.passwordEdit);

        login_handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().GetGroupInfoDetail(abc),1);
                return false;
            }
        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Tabbar tabbar = Tabbar.values()[1];
                tabbar.startActivity(MainActivity.this);
                return false;
            }
        });
        handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(),"잘못된 아이디, 비밀번호 입니다.",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.signin:
                abc = editText1.getText().toString();
                String pass = editText2.getText().toString();
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().getLogin(abc,pass),1);
                break;
            case R.id.signup:
                Intent intent = new Intent(this,SignupActivity.class);
                startActivityForResult(intent,0);
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyInfo myInfo = new MyInfo();
       // editText1.setText("");
       // editText2.setText("");
        if (myInfo.getIsgroup() && myInfo.getFlag() == 1){
            myInfo.setFlag(0);
            Tabbar tabbar = Tabbar.values()[1];
            tabbar.startActivity(MainActivity.this);
        }

    }
}
