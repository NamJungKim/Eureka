package kr.ac.kpu.Eureka.Data;

import android.app.Application;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Global extends Application {

    public static MyInfo myinfo = new MyInfo();
    public static ArrayList<MyGroup> entireGroups = new ArrayList<>();
    public static ArrayList<MyInfo> room_myinfo = new ArrayList<>();
    public static LinearLayout linearLayout; //툴바와 탭바의 색상변경을 위한 변수
    public static int relative_flag = 3; //세팅창 툴바색
    public static Button inRoom_Button;
    public static Button detail_Button;
    public static Button setting_Button;
    @Override
    public void onCreate() {
        super.onCreate();
    }

}