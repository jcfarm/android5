package com.example.kai.qfarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kai on 2017/5/9.
 */

public class cardview_Tab3_Fragment extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private android.widget.SimpleAdapter mAdapter;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_tab1);

        initViewTab1();
        
        initDatasTab1();










    }

    private void initDatasTab1() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i<='z' ; i++){
            mDatas.add(""+(char)i);
        }
    }

    private void initViewTab1() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerViewTab1);
    }
}
