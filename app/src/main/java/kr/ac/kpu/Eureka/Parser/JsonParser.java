package kr.ac.kpu.Eureka.Parser;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyGroup;
import kr.ac.kpu.Eureka.Data.MyInfo;
import kr.ac.kpu.Eureka.HomeFragment;
import kr.ac.kpu.Eureka.Main.MainActivity;
import kr.ac.kpu.Eureka.Main.SignupActivity;

/**
 * Created by han on 2017-05-29.
 * DB 불러오는 클래스
 */


// JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().CreateUser(uuids,names,1,25,"서울"),1);
public class JsonParser { // 싱글톤 패턴 적용
    private volatile static JsonParser instance;
    private JsonParser(){ } // 생성자
    public static JsonParser getInstance() {
        if (instance == null) { // instance 동적 할당 안됬을 경우
            synchronized (JsonParser.class) { // 한 스레드가 메소드를 사용하기 전까지 다른스레드는 대기함.
                if (instance == null) instance = new JsonParser(); // 동적할당
            }
        }
        return instance;
    }

    static RequestQueue queue = null;
    public JsonParser(Context con){
        this.queue = Volley.newRequestQueue(con);
    }

    public static void SetConetxt(Context con){
        queue = Volley.newRequestQueue(con);
    }

    final String User_URL = "http://taxi.linears.xyz:3001/acc";
    final String gro_URL = "http://taxi.linears.xyz:3001/gro";

    public StringRequest CreateUser (final String userid, final String passwd, // 회원 정보 DB에 저장 , 회원가입
                                        final String phoneNumber, final String name, final String email) { // 회원 리스트 DB에 저장
        String url = User_URL;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject data = new JSONObject(response);
                            if( data.getString("result").equals("success") ) {
                                SignupActivity.handler2.sendEmptyMessage(0);
                                return;
                            }
                        }catch (Exception e ){e.printStackTrace(); }
                        SignupActivity.handler.sendEmptyMessage(0);
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        SignupActivity.handler.sendEmptyMessage(0);
                        Log.d("Error.Response", "1");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", userid);
                params.put("passwd", passwd);
                params.put("phoneNumber",phoneNumber); // 0 여자 1 남자
                params.put("name", name);// 1~60
                params.put("email", email); // 0~18 서울 부산 인천 대구 대전 광주 울산 창원 수원 경기 강원 충남 충북 경남 경북 전남 전북 제주 해외
                return params;
            }
        };
        queue.add(postRequest);
        return postRequest;
    }

    public StringRequest CreateGroup (final String departure, final String destination, // 회원 정보 DB에 저장 , 회원가입
                                      final String name, final String maxCount, final String date) { // 회원 리스트 DB에 저장
        String url = gro_URL;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject getid = new JSONObject(response);
                            getid = getid.getJSONObject("data");
                            String _id = getid.getString("_id");
                            JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().PutUser(Global.myinfo.get_userid(), _id), 1); // 계정 DB에도 그룹리스트 추가
                            Log.d("Response", response);
                        }catch (Exception e){e.printStackTrace();}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "1");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("date", date);
                params.put("departure", departure);
                params.put("destination", destination);
                params.put("name",name);
                params.put("maxCount", maxCount);
                return params;
            }
        };
        queue.add(postRequest);
        return postRequest;
    }

    public StringRequest PutUser (final String id, final String roomid) { // 회원 리스트 DB에 저장
        String url = User_URL;
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        HomeFragment.handler.sendEmptyMessage(0); // 프레그먼트 갱신
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "1");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("roomid", roomid);
                return params;
            }
        };
        queue.add(postRequest);
        return postRequest;
    }


    public void SetMyInfo(final String Userid) {
        final String url = User_URL;
        String Param = "?id="+Userid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+Param, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("result").equals("fail")) {
                                MainActivity.handler2.sendEmptyMessage(0);
                                return;
                            }
                        }catch (Exception es) { es.printStackTrace(); }
                        try {
                            JSONObject GroupList = response.getJSONObject("data");
                            try {
                                JSONObject data1 = GroupList;
                                MyState = data1;
                                MyInfo myInfo = new MyInfo();
                                myInfo.set_id(data1.getString("_id"));
                                myInfo.set_userid(data1.getString("userid"));
                                myInfo.set_phone(data1.getString("phoneNumber"));
                                myInfo.set_name(data1.getString("name"));
                                myInfo.set_email(data1.getString("email"));
                                //JSONArray group = data1.getJSONArray("group");
                            } catch (Exception e) {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MainActivity.handler2.sendEmptyMessage(0);
                Log.i("error", "error");
            }
        });
    }
    public JsonObjectRequest getLogin(final String Userid,final String passwd) {
        final String url = User_URL;
        String Param = "?id="+Userid+"&passwd="+passwd;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+Param, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("result").equals("fail")) {
                                MainActivity.handler2.sendEmptyMessage(0);
                                return;
                            }
                        }catch (Exception es) { es.printStackTrace(); }
                        try {
                            try {
                                MainActivity.login_handler.sendEmptyMessage(0);
                                return;
                            } catch (Exception e) {
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MainActivity.handler2.sendEmptyMessage(0);
                Log.i("error", "error");
            }
        });
        queue.add(jsonObjectRequest);
        return jsonObjectRequest;
    }

    public JSONObject MyState = null;
    public JsonObjectRequest GetGroupInfoDetail(final String Userid) {
        final String url = User_URL;
        String Param = "?id="+Userid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+Param, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("result").equals("fail")) {
                                MainActivity.handler2.sendEmptyMessage(0);
                                return;
                            }
                        }catch (Exception es) { es.printStackTrace(); }
                        try {
                            JSONObject GroupList = response.getJSONObject("data");
                            try {
                                JSONObject data1 = GroupList;
                                MyState = data1;
                                String _id = data1.getString("_id");
                                String userid = data1.getString("userid");
                                String phoneNumber = data1.getString("phoneNumber");
                                String name = data1.getString("name");
                                String email = data1.getString("email");
                                JSONArray group = data1.getJSONArray("group");
                                Global.myinfo.set_id(_id);
                                Global.myinfo.set_userid(userid);
                                Global.myinfo.set_email(phoneNumber);
                                Global.myinfo.set_name(name);
                                Global.myinfo.set_phone(email);
                                for (int i =0 ; i<group.length();i++) {
                                    String a = group.getJSONObject(i).getString("_id");
                                    String b = group.getJSONObject(i).getString("departure");
                                    String c =  group.getJSONObject(i).getString("destination");
                                    String d = group.getJSONObject(i).getString("name");
                                    String e = group.getJSONObject(i).getString("date");
                                    String f = group.getJSONObject(i).getString("maxCount");
                                    JSONArray g = group.getJSONObject(i).getJSONArray("member");
                                    MyGroup group_temp = new MyGroup(a,b,c,d,e,f,String.valueOf(g.length()));
                                    Global.myinfo.groups.add(group_temp);
                                }
                                Log.i("한로그", _id +"님이 접속하였습니다.");
                                MainActivity.handler.sendEmptyMessage(0);
                            } catch (Exception e) {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MainActivity.handler2.sendEmptyMessage(0);
                Log.i("error", "error");
            }
        });
        queue.add(jsonObjectRequest);
        return jsonObjectRequest;
    }
    public JsonObjectRequest GetGroupUsers(final String Userid) {
        final String url = gro_URL;
        String Param = "?id="+Userid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+Param, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject GroupList = response.getJSONObject("data");
                            String a = GroupList.getString("_id");
                            String b = GroupList.getString("departure");
                            String c = GroupList.getString("destination");
                            String d = GroupList.getString("name");
                            JSONArray group = GroupList.getJSONArray("member");
                            for(int i=0;i<group.length();i++){
                                JSONObject datas = group.getJSONObject(i);
                                String _id = datas.getString("_id");
                                String userid = datas.getString("userid");
                                String phoneNumber = datas.getString("phoneNumber");
                                String name = datas.getString("name");
                                String email = datas.getString("email");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MainActivity.handler2.sendEmptyMessage(0);
                Log.i("error", "error");
            }
        });
        queue.add(jsonObjectRequest);
        return jsonObjectRequest;
    }


    public void SetRequestQueue(final Request a,final int state) {
        final RequestQueue.RequestFinishedListener listener =
                new RequestQueue.RequestFinishedListener() {
                    @Override
                    public void onRequestFinished(Request request) { // jsonObjectRequest
                        if (request.equals(a)) {
                            if(state == 1){ // 로그인 하라고 전달
                            }
                        }
                    }
                };
        queue.addRequestFinishedListener(listener);
    }
}
