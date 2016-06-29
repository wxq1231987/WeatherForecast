package com.wangxuqin.weatherforecast.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by acer on 2016/6/2.
 */
public class WeatherInfo implements Serializable {
    private City city;
    private WeatherTile tilt;
    private TodayWeather today;
    private ArrayList<DailyWeather> dailyList;

    public void setCity(City city) {
        this.city = city;
    }

    public void setTilt(WeatherTile tilt) {
        this.tilt = tilt;
    }

    public void setToday(TodayWeather today) {
        this.today = today;
    }

    public void setDaily(ArrayList<DailyWeather> dailyList) {
        this.dailyList = dailyList;
    }

    public City getCity() {
        return city;
    }

    public WeatherTile getTilt() {
        return tilt;
    }

    public TodayWeather getToday() {
        return today;
    }

    public ArrayList<DailyWeather> getDaily() {
        return dailyList;
    }
}
