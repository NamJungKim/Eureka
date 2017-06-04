package kr.ac.kpu.Eureka.Settings;

/**
 * Created by namjung on 2017. 6. 4..
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.R;

public class SettingFragment extends Fragment {
    TextView profile;
    View view;
    TextView red,blue,green,yellow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragmentsetting, container, false);
        profile = (TextView)view.findViewById(R.id.profile);
        red = (TextView)view.findViewById(R.id.red);
        blue = (TextView)view.findViewById(R.id.blue);
        green = (TextView)view.findViewById(R.id.green);
        yellow = (TextView)view.findViewById(R.id.yellow);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.relative_flag=0;
                Global.linearLayout.setBackgroundColor(Color.rgb(255,51,51));
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.relative_flag=1;
                Global.linearLayout.setBackgroundColor(Color.rgb(51,255,51));
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.relative_flag=2;
                Global.linearLayout.setBackgroundColor(Color.rgb(51,51,255));
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.relative_flag=3;
                Global.linearLayout.setBackgroundColor(Color.rgb(255,220,60));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Global.linearLayout.setBackgroundColor(Color.rgb(255,0,0));
                //profile.setBackgroundColor(Color.rgb(255,0,0));
                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
