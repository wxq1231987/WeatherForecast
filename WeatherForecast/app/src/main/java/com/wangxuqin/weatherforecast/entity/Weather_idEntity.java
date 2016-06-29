package com.wangxuqin.weatherforecast.entity;

import java.io.Serializable;

/**
 * Created by acer on 2016/6/8.
 */
public class Weather_idEntity implements Serializable{
    /**
     * fa : 08
     * fb : 08
     */
    private String fa;
    private String fb;

    public Weather_idEntity() {
    }

    public Weather_idEntity(String fa, String fb) {
        this.fa = fa;
        this.fb = fb;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getFa() {
        return fa;
    }

    public String getFb() {
        return fb;
    }
}
