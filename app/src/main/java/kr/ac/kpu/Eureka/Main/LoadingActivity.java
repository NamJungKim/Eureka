package kr.ac.kpu.Eureka.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import kr.ac.kpu.Eureka.R;

public class LoadingActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);       
		setContentView(R.layout.activity_loading);
		startLoading();
	}    
	
	private void startLoading(){       
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {            
		
			@Override           
			public void run() {                
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);                
				finish();            
			}    
		},2500);    
	}
}
