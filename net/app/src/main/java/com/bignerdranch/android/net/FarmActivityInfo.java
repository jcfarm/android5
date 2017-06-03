package com.bignerdranch.android.net;

import android.graphics.Bitmap;
import android.media.Image;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Far-away on 17/5/9.
 */

public class FarmActivityInfo {
    private String id;
    private String temp;
    private String name;
    private String humidity;
    private String ph;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getIllumination() {
        return ph;
    }

    public void setIllumination(String illumination) {
        this.ph = illumination;
    }

    public FarmActivityInfo(String id, String temp, String humidity, String ph,String name) {
        this.id = id;
        this.temp = temp;
        this.humidity = humidity;
        this.ph = ph;
        this.name=name;
    }
}
