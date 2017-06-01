package com.example.kai.qfarm;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ShujuMainActivity extends AppCompatActivity {


    private ListView mListView;

    private JavaAdapter mAdapter;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);

        mListView = (ListView)findViewById(R.id.listView);
        mAdapter= new JavaAdapter(this);
        String url = "http://192.168.191.2:8080/Filed/fileddate.txt";


        new HttpJson(url,mListView,mAdapter,mHandler).start();
    }
}
