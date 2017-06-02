package kr.ac.kpu.Eureka;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RoomFragment extends Fragment {
    MyInfo myInfo;
    View nextView;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            myInfo = new MyInfo();
            if(myInfo.getIsgroup()){ //방이 있으면 fragment_inroom 방으로 이동
                nextView = inflater.inflate(R.layout.fragmnet_createroom, container, false);
                return inflater.inflate(R.layout.frament_inroom,container,false);
            }else{ //방이 없으면 fragment_createroom 으로 이동
                return inflater.inflate(R.layout.fragmnet_createroom, container, false);
            }
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if(!myInfo.getIsgroup()) {
                Button btn = (Button) view.findViewById(R.id.createRoom);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CreateRoom.class);
                        startActivityForResult(intent, 0);
                    }
                });
            }
        }

    @Override
    public void onStart() {
        super.onStart();
    }
}
