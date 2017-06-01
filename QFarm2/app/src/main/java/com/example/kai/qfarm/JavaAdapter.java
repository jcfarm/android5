package com.example.kai.qfarm;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by zhaojiang on 17/5/31.
 */

public class JavaAdapter extends BaseAdapter {


    private List <Member> mList;
    private Context mContext;


    private Handler mHandler = new Handler();
    private LayoutInflater mInflater;

    public JavaAdapter(Context context, List<Member> list){
        this.mList = list;
        this.mContext = context;

        mInflater = LayoutInflater.from(context);
    }


    public  JavaAdapter(Context context){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Member> data){
        this.mList = data;

    }


    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.activity_shuju_main,null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        Member member = mList.get(position);
        holder.temp.setText(member.getTemp());
        holder.humidity.setText(member.getHumidity());
        holder.illumination.setText(member.getIllumination());
        holder.co2.setText(member.getCo2());



        new HttpImage(member.getUrl(),mHandler,holder.mImageView).start();

        return convertView;
    }

    class Holder{

        private TextView temp;
        private TextView humidity;
        private TextView illumination;
        private TextView co2;

        private ImageView mImageView;

        public Holder(View view){
            temp = (TextView)view.findViewById(R.id.temp);

            humidity = (TextView)view.findViewById(R.id.humidity);
            illumination = (TextView)view.findViewById(R.id.illumination);
            co2 = (TextView)view.findViewById(R.id.co2);

            mImageView = (ImageView)view.findViewById(R.id.imageView3);

        }

    }
}
