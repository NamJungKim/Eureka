package kr.ac.kpu.Eureka;

/**
 * Created by namjung on 2017. 6. 1..
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateRoom extends AppCompatActivity {

    String startArea = "";
    String endArea = "";
    String dateTime = "";
    String hourTime = "";
    String minTime = "";
    String peopleCnt = "";

    EditText title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createroom);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //반투명 처리
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        //getWindow().setAttributes(lp);
        //배경투명처리
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);*/
        final Spinner start = (Spinner)findViewById(R.id.start); //출발지

        Spinner end = (Spinner)findViewById(R.id.end); // 목적지

        Spinner people = (Spinner)findViewById(R.id.people); // 인원수

        Spinner date = (Spinner)findViewById(R.id.date); //오전,오후

        Spinner hour = (Spinner)findViewById(R.id.hour); // 시간

        Spinner min = (Spinner)findViewById(R.id.min); // 분

        Button canel = (Button)findViewById(R.id.cancel);

        Button ok = (Button)findViewById(R.id.ok);

        title = (EditText)findViewById(R.id.title);

        canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setIntent(intent);
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringTitle = title.getText().toString();
                if (stringTitle.length() != 0) {
                    MyInfo my = new MyInfo();
                    my.setIsgroup();
                    MyGroup mgp = new MyGroup();
                    mgp.setStart_area(startArea);
                    mgp.setEnd_area(endArea);
                    mgp.setDateTimer(dateTime);
                    mgp.setHourTime(hourTime);
                    mgp.setMinTimte(minTime);
                    mgp.setPeopleCnt(peopleCnt);
                    mgp.setTitle(stringTitle);
                    mgp.setFlag(1);
                    /*Tabbar tabbar = Tabbar.values()[1];
                    tabbar.startActivity(CreateRoom.this);*/
                    Intent intent = new Intent();
                    setIntent(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"제목이 비었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                startArea = parent.getItemAtPosition(position).toString();
                //        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                endArea = parent.getItemAtPosition(position).toString();
                //        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                dateTime = parent.getItemAtPosition(position).toString();
                //        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                hourTime = parent.getItemAtPosition(position).toString();
                //        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                minTime = parent.getItemAtPosition(position).toString();
                //        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                peopleCnt = parent.getItemAtPosition(position).toString();
                //        parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }
}
