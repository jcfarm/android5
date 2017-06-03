package com.bignerdranch.android.net;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FarmActivity extends AppCompatActivity {

    private ImageView returnImage;
    private RecyclerView recyclerView;

    private List<FarmActivityInfo> fieldsInfo=new ArrayList<>();
    public String TAG ="1111";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        new GetFieldsTask().execute();

       // returnImage = (ImageView) findViewById(R.id.returnimage);
        recyclerView = (RecyclerView) findViewById(R.id.fields_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        Log.d(TAG, "before");
        recyclerView.setAdapter(new SoundAdapter(fieldsInfo));
        Log.d(TAG, "after ");


    }

    private class SoundHodler extends RecyclerView.ViewHolder  {

        private LinearLayout fieldsLinearLayout;
        private TextView tempTextView;
        private TextView humidityTextView;
        private TextView phTextView;
        private TextView fieldIdTextView;
        private TextView fieldNameTextView;

        public SoundHodler(View view) {
            super(view);
            fieldsLinearLayout= (LinearLayout)view.findViewById(R.id.fields_list_item_linear_layout);
            tempTextView= (TextView)view.findViewById(R.id.temp_text_view);
            humidityTextView= (TextView)view.findViewById(R.id.humidity_text_view);
            phTextView= (TextView)view.findViewById(R.id.ph_text_view);
            fieldIdTextView=(TextView)view.findViewById(R.id.field_id_text_view);
            fieldNameTextView= (TextView) view.findViewById(R.id.field_name_text_view);

            //super(inflater.inflate(R.layout.fields_list_item, container, false));
            //fieldsLinearLayout = (LinearLayout) itemView.findViewById(R.id.fields_list_item_linear_layout);
        }
        public void bindHolder(FarmActivityInfo field){

            tempTextView.setText(field.getTemp());
            humidityTextView.setText(field.getHumidity());
            phTextView.setText(field.getIllumination());
            fieldIdTextView.setText(field.getId()+"号田");
            fieldNameTextView.setText(field.getName());

        }


    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHodler> {
        private List<FarmActivityInfo> fields;

        public SoundAdapter(List<FarmActivityInfo> fields) {
            this.fields = fields;
        }

        @Override
        public SoundHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(FarmActivity.this);
            View view=inflater.inflate(R.layout.fields_list_item,parent,false);

            return new SoundHodler(view);
        }

        @Override
        public void onBindViewHolder(SoundHodler soundHodler, int position) {
            FarmActivityInfo field=fields.get(position);
            soundHodler.bindHolder(field);

        }

        @Override
        public int getItemCount() {
            return fields.size();
        }


    }

    private class GetFieldsTask extends AsyncTask<Void,Void,List<FarmActivityInfo>>{
        @Override
        protected void onPostExecute(List<FarmActivityInfo> farmActivityInfos) {
            fieldsInfo=farmActivityInfos;
            Log.d(TAG, "onPostExecute: "+fieldsInfo.get(1).getId());
            recyclerView.setAdapter(new SoundAdapter(fieldsInfo));

        }

        @Override
        protected List<FarmActivityInfo> doInBackground(Void... params) {
            return new GetInfo().getFields();
        }
    }

    public class TD<T> extends HandlerThread {
        public TD(String name) {
            super(name);
        }
    }
}
