package com.wangxuqin.weatherforecast.entity;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by acer on 2016/6/1.
 */
public class WeatherTile implements Serializable {
    /**
     * wind_strength : 2级
     * time : 22:04
     * humidity : 87%
     * wind_direction : 北风
     * temp : 22
     */
    private String wind_strength;
    private String time;
    private String humidity;
    private String wind_direction;
    private String temp;

    public WeatherTile() {
    }

    public WeatherTile(String wind_strength, String time, String humidity, String wind_direction, String temp) {
        this.wind_strength = wind_strength;
        this.time = time;
        this.humidity = humidity;
        this.wind_direction = wind_direction;
        this.temp = temp;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind_strength() {
        return wind_strength;
    }

    public String getTime() {
        return time;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public String getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "WeatherTile{" +
                "wind_strength='" + wind_strength + '\'' +
                ", time='" + time + '\'' +
                ", humidity='" + humidity + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", temp='" + temp + '\'' +
                '}';
    }
}
