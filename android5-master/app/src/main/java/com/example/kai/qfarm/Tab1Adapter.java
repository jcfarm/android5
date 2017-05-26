package com.example.kai.qfarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kai on 2017/5/10.
 */

public class Tab1Adapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public Tab1Adapter(Context context, List<String> datas){
        this.mContext= context;
        this.mDatas  = datas;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int pos) {
        holder.tv1.setText(mDatas.get(pos));

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = mInflater.inflate(R.layout.item_single_textview ,arg0 ,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv1;

    public MyViewHolder(View agr0) {
        super(agr0);
        tv1 = (TextView) agr0.findViewById(R.id.id_tv1);
    }
}