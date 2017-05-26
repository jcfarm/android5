package com.example.kai.qfarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoggingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton_land;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);

        mButton_land = (Button)findViewById(R.id.button_land);
        mButton_land.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoggingActivity.this,HomeActivity.class);
        startActivity(intent);

    }
}
