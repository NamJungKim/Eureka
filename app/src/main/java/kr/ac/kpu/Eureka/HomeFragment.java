package kr.ac.kpu.Eureka;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.ListViewItem;
import kr.ac.kpu.Eureka.Data.MyGroup;

/**
 * Created by namjung on 2017. 5. 23..
 */

public class HomeFragment extends ListFragment {
    ListViewAdapter adapter;
    public static Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        adapter = new ListViewAdapter();
        setListAdapter(adapter);

        for(int i =0; i< Global.myinfo.groups.size();i++) {
            MyGroup data = Global.myinfo.groups.get(i);
            // 프레그먼트 ui 수정이 필요함 title바 넣는 공간이 필요함 + 채워진 인원수..
            String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_car), data.getStart_area() + " -> " + data.getEnd_area(),time ,data.getGroup_id());
        }
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                adapter = new ListViewAdapter();
                setListAdapter(adapter);
                for(int i =0; i< Global.myinfo.groups.size();i++) {
                    MyGroup data = Global.myinfo.groups.get(i);
                    // 프레그먼트 ui 수정이 필요함 title바 넣는 공간이 필요함 + 채워진 인원수..
                    String time = data.getDateTime() + " " + data.getHourTime() + "시 " + data.getMinTimte() + "분";
                    adapter.addItem(ContextCompat.getDrawable(getContext(),
                            R.drawable.ic_action_car), data.getStart_area() + " -> " + data.getEnd_area(),time ,data.getGroup_id());
                }

                return false;
            }
        });


        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public static String click_id;
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ListViewItem item = (ListViewItem)l.getItemAtPosition(position);
        click_id = item._id;
        Intent intent = new Intent(getActivity(),RoomDetailAcivity.class);
        startActivityForResult(intent,0);
        //등등 코드 입력
    }
}
