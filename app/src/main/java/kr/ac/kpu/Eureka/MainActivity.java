package kr.ac.kpu.Eureka;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    EditText id,password;
    public static Handler handler;
    public static Handler handler2;

    EditText editText1 ;
    EditText editText2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonParser.SetConetxt(this);
        editText1 = (EditText) findViewById(R.id.idEdit);
        editText2= (EditText) findViewById(R.id.passwordEdit);
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
                String user = editText1.getText().toString();
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().GetGroupInfoDetail(user),1);

                // JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().CreateUser(uuids,names,1,25,"서울"),1);
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
        MyGroup mgp = new MyGroup();
        if (myInfo.getIsgroup() && mgp.getFlag() == 1){
            mgp.setFlag(0);
            Tabbar tabbar = Tabbar.values()[1];
            tabbar.startActivity(MainActivity.this);
        }
    }
}
