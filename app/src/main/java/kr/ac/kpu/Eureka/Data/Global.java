package kr.ac.kpu.Eureka.Data;
import android.app.Application;
import android.content.res.Configuration;

public class Global extends Application {

    public static MyInfo myinfo = new MyInfo();
    @Override
    public void onCreate() {
        super.onCreate();
    }

}