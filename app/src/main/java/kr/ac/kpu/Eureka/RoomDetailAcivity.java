package kr.ac.kpu.Eureka;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyGroup;
import kr.ac.kpu.Eureka.Parser.JsonParser;

/**
 * Created by namjung on 2017. 5. 23..
 */

public class RoomDetailAcivity extends Activity{

    TextView text1, text2, text3, text4, text5;
    Button btn1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdetail);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //반투명 처리
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        //getWindow().setAttributes(lp);
        //배경투명처리
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        text1 = (TextView)findViewById(R.id.title);
        text2 = (TextView)findViewById(R.id.dep);
        text3 = (TextView)findViewById(R.id.des);
        text4 = (TextView)findViewById(R.id.time);
        text5 = (TextView)findViewById(R.id.count);

        btn1 = (Button)findViewById(R.id.buttons);

        // 텍스트박스 갱신
        for(int i = 0; i< Global.myinfo.groups.size(); i++) {
            if(Global.myinfo.groups.get(i).getGroup_id().equals(HomeFragment.click_id)) {
                MyGroup data = Global.myinfo.groups.get(i);

                text1.setText("방 제목 : " + data.gettitle());
                text2.setText("출발지 : " + data.getStart_area());
                text3.setText("목적지 : " + data.getEnd_area());
                String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
                text4.setText("출발 시간 : " + time);
                String counts = data.getPresentpeopleCnt() + " / " + data.getPeopleCnt();
                text5.setText("참여 인원: " + counts);
            }
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 시 프레그먼트 전환할 것
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().PutUser(Global.myinfo.get_userid(), String.valueOf(HomeFragment.click_id)), 1); // 디비에 추가
                Toast.makeText(getApplicationContext(),"참가되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

}