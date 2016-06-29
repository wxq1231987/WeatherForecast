package com.wangxuqin.weatherforecast.entity;

import java.io.Serializable;

/**
 * Created by acer on 2016/6/1.
 */
public class TodayWeather implements Serializable {
    /**
     * wind : 西北风3-4 级
     * uv_index : 最弱
     * travel_index : 较不宜
     * temperature : 22℃~31℃
     * city : 长沙
     * comfort_index :
     * date_y : 2016年06月01日
     * wash_index : 不宜
     * exercise_index : 较不宜
     * weather : 中雨
     * drying_index :
     * weather_id : {"fa":"08","fb":"08"}
     * dressing_advice : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
     * week : 星期三
     * dressing_index : 较舒适
     */
    //TOdayWeather
    private String wind;
    private String uv_index;
    private String travel_index;
    private String temperature;
    private String city;
    private String comfort_index;
    private String date_y;
    private String wash_index;
    private String exercise_index;
    private String weather;
    private String drying_index;
    private Weather_idEntity weather_id;
    private String dressing_advice;
    private String week;
    private String dressing_index;

    public TodayWeather() {
    }

    public TodayWeather(String wind, String uv_index, String travel_index, String temperature, String city, String comfort_index, String date_y, String wash_index, String exercise_index, String weather, String drying_index, Weather_idEntity weather_id, String dressing_advice, String week, String dressing_index) {
        this.wind = wind;
        this.uv_index = uv_index;
        this.travel_index = travel_index;
        this.temperature = temperature;
        this.city = city;
        this.comfort_index = comfort_index;
        this.date_y = date_y;
        this.wash_index = wash_index;
        this.exercise_index = exercise_index;
        this.weather = weather;
        this.drying_index = drying_index;
        this.weather_id = weather_id;
        this.dressing_advice = dressing_advice;
        this.week = week;
        this.dressing_index = dressing_index;
    }

    public TodayWeather(String wind, String uv_index, String travel_index, String temperature, String city, String comfort_index, String date_y, String wash_index, String exercise_index, String weather, String drying_index,  String dressing_advice, String week, String dressing_index) {
        this.wind = wind;
        this.uv_index = uv_index;
        this.travel_index = travel_index;
        this.temperature = temperature;
        this.city = city;
        this.comfort_index = comfort_index;
        this.date_y = date_y;
        this.wash_index = wash_index;
        this.exercise_index = exercise_index;
        this.weather = weather;
        this.drying_index = drying_index;
        this.dressing_advice = dressing_advice;
        this.week = week;
        this.dressing_index = dressing_index;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public void setTravel_index(String travel_index) {
        this.travel_index = travel_index;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public void setWash_index(String wash_index) {
        this.wash_index = wash_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }

    public void setWeather_id(Weather_idEntity weather_id) {
        this.weather_id = weather_id;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    public String getWind() {
        return wind;
    }

    public String getUv_index() {
        return uv_index;
    }

    public String getTravel_index() {
        return travel_index;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }

    public String getComfort_index() {
        return comfort_index;
    }

    public String getDate_y() {
        return date_y;
    }

    public String getWash_index() {
        return wash_index;
    }

    public String getExercise_index() {
        return exercise_index;
    }

    public String getWeather() {
        return weather;
    }

    public String getDrying_index() {
        return drying_index;
    }

    public Weather_idEntity getWeather_id() {
        return weather_id;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public String getWeek() {
        return week;
    }

    public String getDressing_index() {
        return dressing_index;
    }

    @Override
    public String toString() {
        return "TodayWeather{" +
                "wind='" + wind + '\'' +
                ", uv_index='" + uv_index + '\'' +
                ", travel_index='" + travel_index + '\'' +
                ", temperature='" + temperature + '\'' +
                ", city='" + city + '\'' +
                ", comfort_index='" + comfort_index + '\'' +
                ", date_y='" + date_y + '\'' +
                ", wash_index='" + wash_index + '\'' +
                ", exercise_index='" + exercise_index + '\'' +
                ", weather='" + weather + '\'' +
                ", drying_index='" + drying_index + '\'' +
                ", weather_id=" + weather_id +
                ", dressing_advice='" + dressing_advice + '\'' +
                ", week='" + week + '\'' +
                ", dressing_index='" + dressing_index + '\'' +
                '}';
    }
}
