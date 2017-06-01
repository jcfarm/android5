package com.bignerdranch.android.net;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class  MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView mTextView;

    private String urlAddress = "http://cloud.bmob.cn/0906a62b462a3082/getMemberBySex?sex=girl";
    app:srcCompat="@drawable/aa5"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new ButtonClick());
    }

    private class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button:

                    doGet();

                    break;
                default:
                    break;
            }
        }

    }

    private void doGet(){
         class mTask extends AsyncTask<Void,Void,List<Member>> {

            @Override
            protected List<Member> doInBackground(Void... params) {


                final String getUrl = urlAddress;
                try {
                    URL url = new URL(getUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer = new StringBuffer();
                        String readLine = "";
                        while ((readLine = bufferedReader.readLine()) != null) {
                            stringBuffer.append(readLine);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        httpURLConnection.disconnect();
                        Log.d("TAG", stringBuffer.toString());
                        parseDate(stringBuffer.toString());


                    } else {
                        Log.d("TAG", "failed");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Member> list) {

                for (Member member : list) {
                    Log.d("test", member.toString());
                    mTextView.setText(member.toString());

                }

            }
        }
    }







//parsedata
    private void parseDate(String result) {



        List<Member> list = new ArrayList<>();

        try {
            JSONObject jsob = new JSONObject(result);
            JSONArray jsarray = jsob.getJSONArray("list");
            for (int i = 0;i<jsarray.length();i++){
                JSONObject object = jsarray.getJSONObject(i);
                Member member = new Member();
                member.setName(object.getString("name"));
                member.setSex(object.getString("sex"));
                list.add(member);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }







    }




}
