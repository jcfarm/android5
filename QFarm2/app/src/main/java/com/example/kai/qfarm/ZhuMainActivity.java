package com.example.kai.qfarm;

import android.content.Intent;
<<<<<<< Updated upstream
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ZhuMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_main);

        ImageView img = (ImageView)findViewById(R.id.imageButton);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(ZhuMainActivity.this,JianMainActivity.class);
=======
                Intent intent = new Intent(ZhuMainActivity.this,VideoViewDemo.class);
>>>>>>> 5b8d0a22cb3c17885077596f3d5ca7343bba8b18
                startActivity(intent);
            }

        });
        ImageView img2 = (ImageView)findViewById(R.id.imageButton2);
        img2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(ZhuMainActivity.this,JianMainActivity.class);
=======
                Intent intent = new Intent(ZhuMainActivity.this,VideoViewDemo.class);
>>>>>>> 5b8d0a22cb3c17885077596f3d5ca7343bba8b18
                startActivity(intent);
            }
        });
        ImageView img3 = (ImageView)findViewById(R.id.imageButton3);
        img3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent intent = new Intent(ZhuMainActivity.this,JianMainActivity.class);
=======
                Intent intent = new Intent(ZhuMainActivity.this,VideoViewDemo.class);
>>>>>>> 5b8d0a22cb3c17885077596f3d5ca7343bba8b18
                startActivity(intent);
            }
        });
    }
    }
=======
import android.os.AsyncTask;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ZhuMainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    public String TAG ="1111";
    private LinearLayout tiaozhuan;
    private LinearLayout fieldsLinearLayout;

    private List<FarmListInfo> fieldsInfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        new FieldsTask().execute();

        try {
            Class.forName("Android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // returnImage = (ImageView) findViewById(R.id.returnimage);
        recyclerView = (RecyclerView) findViewById(R.id.fields_recycler_1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        Log.d(TAG, "before");
        recyclerView.setAdapter(new SoundAdapter(fieldsInfo));
        Log.d(TAG, "after ");

        tiaozhuan = (LinearLayout) findViewById(R.id.tiandi);

        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhuMainActivity.this,JianMainActivity.class);
                startActivity(intent);
            }

        });


    }
    private class SoundHodler extends RecyclerView.ViewHolder  {


        private TextView tempTextView;
        private TextView humidityTextView;
        private TextView phTextView;
        private TextView fieldIdTextView;
        private TextView fieldNameTextView;

        public SoundHodler(View view) {
            super(view);
            fieldsLinearLayout= (LinearLayout)view.findViewById(R.id.tiandi);
            tempTextView= (TextView)view.findViewById(R.id.temp_text_view);
            humidityTextView= (TextView)view.findViewById(R.id.humidity_text_view);
            phTextView= (TextView)view.findViewById(R.id.ph_text_view);
            fieldIdTextView=(TextView)view.findViewById(R.id.field_id_text_view);
            fieldNameTextView= (TextView) view.findViewById(R.id.field_name_text_view);

            //super(inflater.inflate(R.layout.fields_list_item, container, false));
            //fieldsLinearLayout = (LinearLayout) itemView.findViewById(R.id.fields_list_item_linear_layout);
        }
        public void bindHolder(FarmListInfo field){

            tempTextView.setText(field.getTemp());
            humidityTextView.setText(field.getHumidity());
            phTextView.setText(field.getIllumination());
            fieldIdTextView.setText(field.getId()+"号田");
            fieldNameTextView.setText(field.getName());

        }


    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHodler> {
        private List<FarmListInfo> field2;

        public SoundAdapter(List<FarmListInfo> fields) {
            this.field2 = fields;
        }

        @Override
        public SoundHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(ZhuMainActivity.this);
            View view=inflater.inflate(R.layout.activity_zhu_main,parent,false);

            return new SoundHodler(view);
        }

        @Override
        public void onBindViewHolder(SoundHodler soundHodler, int position) {
            FarmListInfo field=field2.get(position);
            soundHodler.bindHolder(field);

        }

        @Override
        public int getItemCount() {
            return field2.size();
        }


    }

    private class FieldsTask extends AsyncTask<Void,Void,List<FarmListInfo>>{
        @Override
        protected void onPostExecute(List<FarmListInfo> farmActivityInfos) {
            fieldsInfo=farmActivityInfos;

            Log.d(TAG, "onPostExecute: "+fieldsInfo.get(0).getId());

            recyclerView.setAdapter(new SoundAdapter(fieldsInfo));

        }

        @Override
        protected List<FarmListInfo> doInBackground(Void... params) {
            return new GetInfo().getFields();

        }
    }
}
>>>>>>> Stashed changes
