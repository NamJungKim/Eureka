package kr.ac.kpu.Eureka;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

/**
 * Created by namjung on 2017. 5. 23..
 */

public class RoomDetailAcivity extends Activity{

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

    }

}