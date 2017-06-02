package kr.ac.kpu.Eureka.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by namjung on 2017. 6. 1..
 */

public class MyGroup {
     String group_id;
     String start_area;
     String end_area;
     String group_title;
     String dateTime = "";
     String hourTime = "";
     String minTime = "";
     String peopleCnt = ""; //맥스인원수
     String PresentpeopleCnt = ""; // 현재인원수
     int flag = 0;

    public MyGroup(String a,String b,String c,String d,String e,String f,String g){
        this.group_id = a;
        this.start_area = b;
        this.end_area = c;
        this.group_title = d;
        this.peopleCnt = f;
        this.PresentpeopleCnt = g;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = format.parse(e);
            if(date.getHours() > 12) {
                this.dateTime = "오후";
                this.hourTime = String.valueOf(date.getHours() - 12);
            } else{
                this.dateTime = "오전";
                this.hourTime = String.valueOf(date.getHours());
            }
            this.minTime = String.valueOf(date.getMinutes());
        }catch (Exception es) {es.printStackTrace(); }
    }

    public String getGroup_id(){
        return group_id;
    }
    public String getStart_area(){
        return start_area;
    }
    public String getEnd_area(){
        return end_area;
    }
    public  String gettitle(){
        return group_title;
    }
    public String getDateTime(){ return dateTime;}
    public String getHourTime(){ return hourTime;}
    public  String getMinTimte(){ return minTime;}
    public   String getPeopleCnt(){ return peopleCnt;}
    public   void setDateTimer(String date){ dateTime=date;}
    public void setHourTime(String hour){ hourTime=hour;}
    public void setMinTimte(String min){ minTime=min;}
    public void setPeopleCnt(String people){ peopleCnt=people;}
    public  void setStart_area(String area){
        start_area=area;
    }
    public  void setEnd_area(String area){
        end_area=area;
    }
    public  void setTitle(String title){
        group_title=title;
    }
    public int getFlag(){return flag;}
    public  void setFlag(int _flag){flag=_flag;}

    public   String getPresentpeopleCnt(){ return PresentpeopleCnt;}
    public   void setPresentpeopleCnt(String a){ PresentpeopleCnt=a;}
}
