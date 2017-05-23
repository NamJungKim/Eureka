package kr.ac.kpu.Eureka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    EditText id,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.signin:
                Tabbar tabbar = Tabbar.values()[1];
                tabbar.startActivity(this);
                break;
            case R.id.signup:
                Intent intent = new Intent(this,SignupActivity.class);
                startActivityForResult(intent,0);
            default:
                break;
        }
    }
}
