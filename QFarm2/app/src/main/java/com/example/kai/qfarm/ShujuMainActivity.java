package com.example.kai.qfarm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShujuMainActivity extends AppCompatActivity {

    private TextView resultTv;
    private TextView resultTv1;
    private TextView resultTv2;
    private TextView resultTv3;
    private TextView resultTv4;
    private TextView resultTv5;

    private String host = "tcp://100.76.96.38:1883";

    private Handler handler;

    private MqttClient client;

    private String myTopic = "温度";
    private String myTopic2 = "湿度";
    private String myTopic3 = "二氧化碳浓度";
    private String myTopic4 = "co2";
    private String myTopic5 = "土壤水分";
    private String myTopic6 = "光照强度";

    private MqttConnectOptions options;

    private ScheduledExecutorService scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuju_main);

        resultTv = (TextView) findViewById(R.id.temp);
        resultTv1 =(TextView) findViewById(R.id.humidity);
        resultTv2 =(TextView) findViewById(R.id.co2);
        resultTv3 =(TextView) findViewById(R.id.textView4);
        resultTv4 =(TextView) findViewById(R.id.textView5);
        resultTv5 =(TextView) findViewById(R.id.illumination);
        init();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1) {
                    resultTv.setTextSize(30);
                    resultTv1.setTextSize(30);
                    resultTv2.setTextSize(30);
                    resultTv3.setTextSize(30);
                    resultTv4.setTextSize(30);
                    resultTv5.setTextSize(30);
                    String title = msg.obj.toString().split(":")[0];
                    switch (title){
                        case "温度":resultTv.setText((String) msg.obj);break;
                        case "湿度":resultTv1.setText((String) msg.obj);break;
                        case "二氧化碳浓度":resultTv2.setText((String) msg.obj);break;
                        case "co2":resultTv3.setText((String) msg.obj);break;
                        case "土壤水分":resultTv4.setText((String) msg.obj);break;
                        case "光照强度":resultTv5.setText((String) msg.obj);break;
                    }


                    System.out.println("-----------------------------");
                } else if(msg.what == 2) {
                    resultTv.setTextSize(20);
                    resultTv.setText("连接成功!");
                    try {
                        client.subscribe(myTopic, 1);
                        client.subscribe(myTopic2, 1);
                        client.subscribe(myTopic3, 1);
                        client.subscribe(myTopic4, 1);
                        client.subscribe(myTopic5, 1);
                        client.subscribe(myTopic6, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if(msg.what == 3) {
                    resultTv.setTextSize(20);
                    resultTv.setText("连接失败，系统正在重连...");
                }
            }
        };

        startReconnect();

    }

    private void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                if(!client.isConnected()) {
                    connect();
                }
            }
        }, 0 * 1000, 1 * 1000, TimeUnit.MILLISECONDS);
    }

    private void init() {
        try {
            //host为主机名，test为clientid即连接MQTT的客户端id，id为内存保存形式
            client = new MqttClient(host, "test", new MemoryPersistence());
            //MQTT的连接设置
            options = new MqttConnectOptions();
            //设置是否清空session
            options.setCleanSession(true);
            // 设置超时时间
            options.setConnectionTimeout(10);
            // 设置会话心跳时间
            options.setKeepAliveInterval(20);
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    //连接丢失后，重连
                    System.out.println("connectionLost----------");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe
                    System.out.println("messageArrived----------");
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = topicName+":"+message.toString();
                    handler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    client.connect(options);
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(client != null && keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                client.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            scheduler.shutdown();
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}