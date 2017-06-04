package kr.ac.kpu.Eureka.Inroom;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyGroup;
import kr.ac.kpu.Eureka.Data.MyInfo;
import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.Room.MeetingItem;

public class inroom_activity extends Activity {

  RecyclerView recyclerView;
  inroomAdapter adapter;
  ArrayList<inroomItem> roomsItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_inroomdetail);

      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      WindowManager.LayoutParams lp = getWindow().getAttributes();
     // lp.alpha = 0.9f;
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

      roomsItems = new ArrayList<>();
      recyclerView = (RecyclerView) findViewById(R.id.recycle);

      for(int i = 0; i< Global.room_myinfo.size(); i++) {
          MyInfo data = Global.room_myinfo.get(i);
          inroomItem meetingItem1 = new inroomItem(data.get_userid(),data.get_name(),data.get_email(),data.get_phone());
          roomsItems.add(meetingItem1);
      }
        adapter = new inroomAdapter(getApplicationContext(), roomsItems);

      LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setAdapter(adapter);
      recyclerView.setHasFixedSize(true);

      Button btn1 = (Button) findViewById(R.id.buttons);
      btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          finish();
        }
      });
    }

}
