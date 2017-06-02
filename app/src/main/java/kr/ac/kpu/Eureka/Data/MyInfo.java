package kr.ac.kpu.Eureka.Data;

import java.util.ArrayList;

/**
 * Created by namjung on 2017. 6. 1..
 */

public class MyInfo {
    private String _id;
    private String _userid;
    private String _name;
    private String _email;
    private String _phone;
    static boolean isgroup=false;
    static int flag = 0;
    public ArrayList<MyGroup> groups = new ArrayList<>();

    public void setIsgroup(){
        isgroup = !isgroup;
    }
    public boolean getIsgroup(){
        return isgroup;
    }
    public void set_id(String id){
        _id = id;
    }
    public void set_userid(String userid){
        _userid=userid;
    }
    public void set_name(String name){
        _name=name;
    }
    public void set_email(String email){
        _email=email;
    }
    public void set_phone(String phone){
        _phone=phone;
    }
    public void setFlag(int _flag){flag=_flag;}
    public String get_id(){
        return _id;
    }
    public  String get_name(){
        return _name;
    }
    public  String get_email(){
        return _email;
    }
    public String get_phone(){
        return _phone;
    }
    public  String get_userid(){
        return _userid;
    }
    public int getFlag(){return flag;}
}
