package com.wangxuqin.weatherforecast.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by acer on 2016/6/10.
 */
public class NetWorkUtil {
    private Context context;
    public NetWorkUtil(Context context) {
        this.context = context;
    }
    /**
     * 判断网络是否连通
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

}
