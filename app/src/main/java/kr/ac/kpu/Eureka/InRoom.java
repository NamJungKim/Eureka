package kr.ac.kpu.Eureka;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyGroup;

public class InRoom extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // 이거 이용해서 짜면 되요
        // HomeFragment.click_id -> 프레그먼트 클릭 시 _id 받아옴
        for(int i = 0; i< Global.myinfo.groups.size(); i++) {
            if (Global.myinfo.groups.get(i).getGroup_id().equals(HomeFragment.click_id)) {
                MyGroup data = Global.myinfo.groups.get(i);

            }
        }
                /*text1.setText("방 제목 : " + data.gettitle());
                text2.setText("출발지 : " + data.getStart_area());
                text3.setText("목적지 : " + data.getEnd_area());
                String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
                text4.setText("출발 시간 : " + time);
                String counts = data.getPresentpeopleCnt() + " / " + data.getPeopleCnt();
                text5.setText("참여 인원: " + counts);
            }
        }*/
        return inflater.inflate(R.layout.frament_inroom,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
