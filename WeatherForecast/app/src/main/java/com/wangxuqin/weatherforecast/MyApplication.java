package com.wangxuqin.weatherforecast;
import android.app.Application;
import com.thinkland.sdk.android.JuheSDKInitializer;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();//getApplicationContext
        JuheSDKInitializer.initialize(getApplicationContext());
    }
}