package kr.ac.kpu.Eureka;

/**
 * Created by namjung on 2017. 6. 1..
 */

public class MyInfo {
    static String _id;
    static String _userid;
    static String _name;
    static String _email;
    static String _phone;
    static boolean isgroup=false;

    void setIsgroup(){
        isgroup = !isgroup;
    }
    boolean getIsgroup(){
        return isgroup;
    }
    void set_id(String id){
        _id = id;
    }
    void set_userid(String userid){
        _userid=userid;
    }
    void set_name(String name){
        _name=name;
    }
    void set_email(String email){
        _email=email;
    }
    void set_phone(String phone){
        _phone=phone;
    }
    String get_id(){
        return _id;
    }
    String get_name(){
        return _name;
    }
    String get_email(){
        return _email;
    }
    String get_phone(){
        return _phone;
    }
    String get_userid(){
        return _userid;
    }
}
