package com.example.kai.qfarm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zhaojiang on 17/6/1.
 */

public class GetInfo  {
    private String fieldUrl = "http://172.20.10.4:8090/FileUpload/fields";
    private OkHttpClient okHttpClient;
    public String TAG="2";



    public String getJsonString(){
        String jsonString=null;
        Request request = new Request.Builder().url(fieldUrl).build();
        okHttpClient=new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {

                jsonString = response.body().string();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "getJsonString: "+ jsonString);
        return jsonString;
    }

    public void parseData(List<FarmListInfo> fields,JSONObject jsonBody) {

        try {


            JSONArray jsonFieldArray = jsonBody.getJSONArray("fields");
            for (int i = 0; i < jsonFieldArray.length(); i++) {
                JSONObject fieldObject = jsonFieldArray.getJSONObject(i);
                FarmListInfo farmActivityInfo = new FarmListInfo(fieldObject.getString("id"), fieldObject.getString("temp"),
                        fieldObject.getString("humidity"), fieldObject.getString("ph"),fieldObject.getString("name"));
                fields.add(farmActivityInfo);
                Log.d(TAG, "parseData: "+ fieldObject.getString("id"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<FarmListInfo> getFields(){
        List<FarmListInfo> fields=new ArrayList<>();
        try {
            String string=getJsonString();
            JSONObject object= new JSONObject(string);
            parseData(fields,object);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "getFields: "+ fields.size());
        return fields;
    }



}
