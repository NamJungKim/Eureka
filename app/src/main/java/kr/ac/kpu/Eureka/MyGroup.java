package kr.ac.kpu.Eureka;

/**
 * Created by namjung on 2017. 6. 1..
 */

public class MyGroup {
    static String group_id;
    static String start_area;
    static String end_area;
    static String group_title;
    static String dateTime = "";
    static String hourTime = "";
    static String minTime = "";
    static String peopleCnt = "";
    static int flag = 0;

    String getGroup_id(){
        return group_id;
    }
    String getStart_area(){
        return start_area;
    }
    String getEnd_area(){
        return end_area;
    }
    String gettitle(){
        return group_title;
    }
    String getDateTime(){ return dateTime;}
    String getHourTime(){ return hourTime;}
    String getMinTimte(){ return minTime;}
    String getPeopleCnt(){ return peopleCnt;}
    void setDateTimer(String date){ dateTime=date;}
    void setHourTime(String hour){ hourTime=hour;}
    void setMinTimte(String min){ minTime=min;}
    void setPeopleCnt(String people){ peopleCnt=people;}
    void setStart_area(String area){
        start_area=area;
    }
    void setEnd_area(String area){
        end_area=area;
    }
    void setTitle(String title){
        group_title=title;
    }
    int getFlag(){return flag;}
    void setFlag(int _flag){flag=_flag;}
}
