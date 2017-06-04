package kr.ac.kpu.Eureka.Settings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.R;

/**
 * Created by namjung on 2017. 6. 4..
 */

public class ProfileActivity extends AppCompatActivity {
    TextView id,name,email,phone;
    Button editbtn;
    ImageButton back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        id = (TextView) findViewById(R.id.id);
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.phone);
        editbtn = (Button)findViewById(R.id.edit);

        id.setText(Global.myinfo.get_userid());
        name.setText(Global.myinfo.get_name());
        email.setText(Global.myinfo.get_email());
        phone.setText(Global.myinfo.get_phone());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.cumtom_actionbar, null);

        back = (ImageButton)actionbar.findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setIntent(intent);
                finish();
            }
        });
        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        if(Global.relative_flag == 0){
            ((RelativeLayout)actionbar.findViewById(R.id.rellayout)).setBackgroundColor(Color.rgb(255,51,51));
        }else if(Global.relative_flag == 1){
            ((RelativeLayout)actionbar.findViewById(R.id.rellayout)).setBackgroundColor(Color.rgb(51,255,51));
        }else if(Global.relative_flag == 2){
            ((RelativeLayout)actionbar.findViewById(R.id.rellayout)).setBackgroundColor(Color.rgb(51,51,255));
        }else if(Global.relative_flag == 3){
            ((RelativeLayout)actionbar.findViewById(R.id.rellayout)).setBackgroundColor(Color.rgb(255,220,60));
        }

        return true;
    }
}
