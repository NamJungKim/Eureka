package kr.ac.kpu.Eureka.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyGroup;
import kr.ac.kpu.Eureka.Inroom.inroom_activity;
import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.Tab.Tabbar;

public class MeetingFragment extends Fragment {

    RecyclerView recyclerView;
    MeetingAdapter meetingAdapter;
    ArrayList<MeetingItem> meetingItems;
    public static Handler handler;
    public static Handler handler2_start;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meeting, container, false);

        meetingItems = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        for(int i = 0; i< Global.myinfo.groups.size(); i++) {
            MyGroup data = Global.myinfo.groups.get(i);
            // 프레그먼트 ui 수정이 필요함 title바 넣는 공간이 필요함 + 채워진 인원수..
            String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
            String counts = data.getPresentpeopleCnt() + " / " + data.getPeopleCnt();
            MeetingItem meetingItem1 = new MeetingItem(data.gettitle(),data.getStart_area() + " -> " + data.getEnd_area(),counts,"",time,data.getGroup_id());
            meetingItems.add(meetingItem1);
        }

       // meetingItems.add(meetingItem);

        meetingAdapter = new MeetingAdapter(getActivity().getApplicationContext(),meetingItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(meetingAdapter);
        recyclerView.setHasFixedSize(true);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                meetingItems =  new ArrayList<>();
                for(int i = 0; i< Global.myinfo.groups.size(); i++) {
                    MyGroup data = Global.myinfo.groups.get(i);
                    String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
                    String counts = data.getPresentpeopleCnt() + " / " + data.getPeopleCnt();
                    MeetingItem meetingItem1 = new MeetingItem(data.gettitle(),data.getStart_area() + " -> " + data.getEnd_area(),counts,"",time,data.getGroup_id());
                    meetingItems.add(meetingItem1);
                }
                meetingAdapter = new MeetingAdapter(getActivity().getApplicationContext(),meetingItems);
                meetingAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(meetingAdapter);
                return false;
            }
        });

        handler2_start = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Intent intent = new Intent(getContext(),inroom_activity.class);
                startActivity(intent);
                return false;
            }
        });

        return view;

    }
}
