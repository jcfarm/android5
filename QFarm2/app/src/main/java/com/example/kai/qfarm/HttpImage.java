package com.example.kai.qfarm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhaojiang on 17/5/31.
 */

public class HttpImage extends Thread {

    private ImageView mImageView;
    private String url;

    private Handler mHandler;


    public HttpImage(String url,Handler handler,ImageView imageView){
        this.mHandler = handler;
        this.url = url;
        this.mImageView = imageView;


    }

    @Override
    public void run() {
        try{
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)httpUrl.openConnection();

            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            InputStream inputStream= connection.getInputStream();

            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);



            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mImageView.setImageBitmap(bitmap);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
