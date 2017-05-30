package kr.ac.kpu.Eureka;

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
                                     final String name, final String maxCount) { // 회원 리스트 DB에 저장
        String url = gro_URL;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
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
