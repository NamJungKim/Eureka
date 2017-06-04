package kr.ac.kpu.Eureka.Home;

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
import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.Room.MeetingAdapter;
import kr.ac.kpu.Eureka.Room.MeetingItem;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter meetingAdapter;
    ArrayList<HomeItem> meetingItems;
    public static Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meeting, container, false);

        meetingItems = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        for(int i =0; i< Global.entireGroups.size();i++) {
            MyGroup data = Global.entireGroups.get(i);
            // 프레그먼트 ui 수정이 필요함 title바 넣는 공간이 필요함 + 채워진 인원수..
            String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
            HomeItem item1 = new HomeItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_car),data.getStart_area() + " -> " + data.getEnd_area(),time ,data.getGroup_id());
            meetingItems.add(item1);
        }

        meetingAdapter = new HomeAdapter(getActivity().getApplicationContext(),meetingItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(meetingAdapter);
        recyclerView.setHasFixedSize(true);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                meetingItems =  new ArrayList<>();
                for(int i =0; i< Global.entireGroups.size();i++) {
                    MyGroup data = Global.entireGroups.get(i);
                    // 프레그먼트 ui 수정이 필요함 title바 넣는 공간이 필요함 + 채워진 인원수..
                    String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
                    HomeItem item1 = new HomeItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_car),data.getStart_area() + " -> " + data.getEnd_area(),time ,data.getGroup_id());
                    meetingItems.add(item1);
                }
                meetingAdapter = new HomeAdapter(getActivity().getApplicationContext(),meetingItems);
                recyclerView.setAdapter(meetingAdapter);
                return false;
            }
        });

        return view;

    }
}
