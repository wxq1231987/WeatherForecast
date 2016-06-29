package com.wangxuqin.weatherforecast.entity;

import java.io.Serializable;

/**
 * Created by acer on 2016/6/5.
 */
public class City implements Serializable {
    private String id;
    private String cityName;//城市名称

    public City() {
    }

    public City(String id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
