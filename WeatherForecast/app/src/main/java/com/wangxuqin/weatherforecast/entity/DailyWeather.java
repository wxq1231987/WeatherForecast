package com.wangxuqin.weatherforecast.entity;

import java.io.Serializable;

/**
 * Created by acer on 2016/6/1.
 */
public class DailyWeather implements Serializable {
    /**
     * wind : 西北风3-4 级
     * weather : 中雨
     * weather_id : {"fa":"08","fb":"08"}
     * date : 20160601
     * week : 星期三
     * temperature : 22℃~31℃
     */
    private String wind;
    private String weather;
    private Weather_idEntity weather_id;
    private String date;
    private String week;
    private String temperature;

    public DailyWeather() {
    }

    public DailyWeather(String wind, String weather, String date, String week, String temperature) {
        this.wind = wind;
        this.weather = weather;
        this.date = date;
        this.week = week;
        this.temperature = temperature;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind() {
        return wind;
    }

    public String getWeather() {
        return weather;
    }


    public String getDate() {
        return date;
    }

    public String getWeek() {
        return week;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "DailyWeather{" +
                "wind='" + wind + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + weather_id +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
