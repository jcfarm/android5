package com.example.kai.qfarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojiang on 17/5/31.
 */

public class HttpJson extends Thread {

    private String url;

    private Context mContext;

    private ListView mListView;
    private JavaAdapter mAdapter;

    private Handler mHandler;

    public HttpJson(String url,ListView listView,JavaAdapter javaAdapter,Handler handler){
        this.url = url;
        this.mListView = listView;
        this.mAdapter = javaAdapter;
        this.mHandler=handler;

    }

    @Override
    public void run() {
        URL httpUrl;
        try{
            httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)httpUrl.openConnection();

            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            InputStream inputStream= connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String str;


            while ((str =reader.readLine())!=null){
                stringBuffer.append(str);
            }


            final List<Member> data = parseJson(stringBuffer.toString());


            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    mAdapter.setData(data);
                    mListView.setAdapter(mAdapter);

                    String url;

                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Member> parseJson(String json){
        try {
            JSONObject object = new JSONObject(json);
            List<Member> memberList = new ArrayList<Member>();


            JSONArray sensors = object.getJSONArray("sensors");
            for (int i = 0 ;i<sensors.length();i++){
                Member memberObject = new Member();

                memberList.add(memberObject);
                JSONObject jsonObject = sensors.getJSONObject(i);
                String temp = jsonObject.getString("temp");
                String id = jsonObject.getString("id");
                String type =  jsonObject.getString("type");
                String humidity = jsonObject.getString("humidity");
                String illumination = jsonObject.getString("illumination");
                String co2 = jsonObject.getString("co2");
                memberObject.setTemp(temp);
                memberObject.setCo2(co2);
                memberObject.setHumidity(humidity);
                memberObject.setId(id);
                memberObject.setIllumination(illumination);
                memberObject.setType(type);

            }

            return memberList;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
