package com.example.kai.qfarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
//    private List<ViewPager> mViewList = new ArrayList<>();
    public String[] mTitles = new String[]{"1", "2", "3"};
//    private View mView1;
//    private View mView2;
//    private View mView3;


    private FragmentPagerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private Tab1Adapter mAdapter1;


    private List<ChangeColorWithText> mTabIndicators = new ArrayList<ChangeColorWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btn = (Button)findViewById(R.id.shexiang);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,VideoViewDemo.class);
                startActivity(intent);

            }
        });

        initView();
        initDatas();
        mViewPager.setAdapter(mAdapter);
        initEvent();

        initDatasTab1();
        initViewTab1();
        mAdapter1 = new Tab1Adapter(this,mDatas);


        mRecyclerView = (RecyclerView)findViewById(R.id.id_recyclerViewTab1) ;
        mRecyclerView.setAdapter(mAdapter1);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }



              private void initDatasTab1() {
                  mDatas = new ArrayList<String>();
                  for (int i = '1'; i<='9' ; i++){
                      mDatas.add(""+(char)i);
                  }
              }

              private void initViewTab1() {
                  mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerViewTab1);
              }




    private void initEvent() {
        mViewPager.setOnPageChangeListener(this);
    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        ChangeColorWithText one = (ChangeColorWithText)findViewById(R.id.tab_1);
        mTabIndicators.add(one);
        ChangeColorWithText two = (ChangeColorWithText)findViewById(R.id.tab_2);
        mTabIndicators.add(two);
        ChangeColorWithText three = (ChangeColorWithText)findViewById(R.id.tab_3);
        mTabIndicators.add(three);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }

    private void initDatas() {
        for (String title : mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TITLE, title);
            tabFragment.setArguments(bundle);

//            LayoutInflater lf = getLayoutInflater().from(this);
//            mView1 = lf.inflate(R.layout.recyclerview_tab1,null);
//
//            mViewList.add((ViewPager) mView1);
            mTabs.add(tabFragment);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }

    //tab按钮事件
    @Override
    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId()){
            case R.id.tab_1:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.tab_2:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1,false);
                break;
            case R.id.tab_3:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2,false);
                break;
        }

    }

    private void resetOtherTabs() {
        for (int i = 0;i<mTabIndicators.size();i++){
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    //滑动事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset>0){
            ChangeColorWithText left = mTabIndicators.get(position);
            ChangeColorWithText right = mTabIndicators.get(position+1);
            left.setIconAlpha(1-positionOffset);
            right.setIconAlpha(position);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}