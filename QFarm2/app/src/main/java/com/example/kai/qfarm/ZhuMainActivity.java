package com.example.kai.qfarm;

import android.content.Intent;
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
                Intent intent = new Intent(ZhuMainActivity.this,VideoViewDemo.class);
                startActivity(intent);
            }

        });
        ImageView img2 = (ImageView)findViewById(R.id.imageButton2);
        img2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhuMainActivity.this,VideoViewDemo.class);
                startActivity(intent);
            }
        });
        ImageView img3 = (ImageView)findViewById(R.id.imageButton3);
        img3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhuMainActivity.this,VideoViewDemo.class);
                startActivity(intent);
            }
        });
    }
    }
