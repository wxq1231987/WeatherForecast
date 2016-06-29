package com.wangxuqin.weatherforecast.ui;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.squareup.picasso.Picasso;
import com.wangxuqin.weatherforecast.R;
import com.wangxuqin.weatherforecast.entity.*;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {
    private String[] weathers=
            new String[]{"晴","多云","阴",
                    "阵雨","雷阵雨","雷阵雨伴有冰雹",
                    "雨夹雪","小雨", "中雨",
                    "大雨","暴雨","大暴雨",
                    "特大暴雨","阵雪","小雪",
                    "中雪", "大雪","暴雪",
                    "雾","冻雨","沙尘暴",
                    "小雨-中雨","中雨-大雨","大雨-暴雨",
                    "暴雨-大暴雨","大暴雨-特大暴雨", "小雪-中雪",
                    "中雪-大雪","大雪-暴雪",
                    "浮尘", "扬沙","强沙尘暴",
                    "霾"};
    private int[] logoes=
            new int[]{R.drawable.weathy_01,R.drawable.weathy_02,R.drawable.weathy_04,
            R.drawable.weathy_05,R.drawable.weathy_10,R.drawable.weathy_10,
            R.drawable.weathy_15,R.drawable.weathy_07,R.drawable.weathy_08,
            R.drawable.weathy_09,R.drawable.weathy_27,R.drawable.weathy_26,
            R.drawable.weathy_29,R.drawable.weathy_30,R.drawable.weathy_11,
            R.drawable.weathy_14,R.drawable.weathy_17,R.drawable.weathy_31,
            R.drawable.weathy_32,R.drawable.weathy_17, R.drawable.weathy_24,
            R.drawable.weathy_33,R.drawable.weathy_34,R.drawable.weathy_34,
            R.drawable.weathy_34,R.drawable.weathy_34,R.drawable.weathy_34,
            R.drawable.weathy_35,R.drawable.weathy_36,R.drawable.weathy_33,
            R.drawable.weathy_37};
    private static final String ARG1="CITY";
    private static final String ARG2="WEATHERTILE";
    private static final String ARG3="TODAYWEATHER";
    private static final String ARG4="DAILYWEATHER";
    private WeatherInfo info;
    private static City city;
    private WeatherTile weatherTile;
    private TodayWeather todayWeather;
    private Weather_idEntity wid;
    private ArrayList<DailyWeather> dailyList;
    @Bind(R.id.cityname_tv)
    TextView cityname_tv;
    @Bind(R.id.temp_tv)
    TextView temp_tv;
    @Bind(R.id.tile_iv)
    ImageView tile_iv;
    @Bind(R.id.weather_tv)
    TextView weather_tv;
    @Bind(R.id.bac_iv)
    ImageView bac_iv;
    @Bind(R.id.min_chart)
    LineChart nLineChart;
    @Bind(R.id.max_chart)
    LineChart   xLineChart;
    private static Context context1;
    public WeatherFragment() {
    }
    public static Fragment getInstance(Context context,
                                        City city,
                                       WeatherTile weatherTile,
                                       TodayWeather todayWeather,
                                       ArrayList<DailyWeather> dailyList) {
        WeatherFragment instance=new WeatherFragment();
        Bundle args=new Bundle();
        context1=context;
        args.putSerializable(ARG1, city);
        args.putSerializable(ARG2, weatherTile);
        args.putSerializable(ARG3, todayWeather);
        args.putSerializable(ARG4, dailyList);
        instance.setArguments(args);
        return instance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据
        city=(City)getArguments().getSerializable(ARG1);
        weatherTile=(WeatherTile)getArguments().getSerializable(ARG2);
        todayWeather=(TodayWeather)getArguments().getSerializable(ARG3);
        dailyList=(ArrayList<DailyWeather>)getArguments().getSerializable(ARG4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        cityname_tv.setText(city.getCityName());
        Log.d("WeatherFragment","验证数据是否传入"+
        String.valueOf(weatherTile==null)+","+String.valueOf(dailyList==null)+","+
                String.valueOf(todayWeather==null));
        if(weatherTile==null&&dailyList==null&&todayWeather==null) {
            temp_tv.setText("28°");
            tile_iv.setImageResource(R.drawable.weathy_01);
            weather_tv.setText("1级东风");
            new TempUI(null,null,null,
                    xLineChart,
                    nLineChart);
        } else {
            wid=todayWeather.getWeather_id();
            temp_tv.setText(weatherTile.getTemp() + "°");
            tile_iv.setImageResource(getWeatherLogo(wid.getFa()));
            String text = weatherTile.getWind_strength() + " " + weatherTile.getWind_direction();
            weather_tv.setText(text);
            new TempUI(getDates(dailyList)
                    ,getMaxTemp(dailyList),
                    getMinTemp(dailyList),
                    xLineChart,
                    nLineChart);
        }
        //设置天气状况的背景图片
        Picasso.with(context1)
                .load("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%95%BF%E6%B2%99&step_word=&pn=4&spn=0&di=97630430590&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1751017965%2C2544039574&os=1291930757%2C3949272085&simid=4266555828%2C954460460&adpicid=0&ln=1000&fr=&fmq=1465717318197_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fcn.travelchina.gov.cn%2Ftirms%2FnewImgTem%2F7611f1a26f64440ab0fe81e49ccaa0ec.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fvg_z%26e3Bp6wejsvitgw_z%26e3B25e_z%26e3BvgAzdH3Fpt64fAzdH3Fu65gpAzdH3Fzi_CNAzdH3Ffr5p_z%26e3B15%3Ftt1%3D0b&gsm=0&rpstart=0&rpnum=0")
                .resize(320,400)
                .centerCrop()
                .placeholder(R.drawable.bac2)
                .into(bac_iv);
        return view;
    }
    /**
     * 根据天气字段获取天气图标
     * @param id 天气描述字符串
     * @return 天气图标的ID
     */
    public int getWeatherLogo(String id) {
        int index= Integer.parseInt(id);
        if(index>31) {
            index=33;
        }
        return logoes[index];
    }
    /**
     * 获取最高温度
     */
    public ArrayList<Integer> getMaxTemp(ArrayList<DailyWeather> dailyWeathers) {
        ArrayList<Integer> maxTemps=new ArrayList<>();
        for(int i=0;i<dailyWeathers.size();i++) {
            String temp_str=dailyWeathers.get(i).getTemperature();
            int index1=temp_str.indexOf("~");
            int index2=temp_str.lastIndexOf("℃");
            Log.d("Chart",String.valueOf(index1));
            Log.d("Chart",String.valueOf(index2));
            int temp=Integer.parseInt(temp_str.substring(index1+1,index2));
            maxTemps.add(temp);
        }
        Log.d("Temp1",maxTemps.toString());
        return maxTemps;
    }
    /**
     * 获取最低温度
     */
    public ArrayList<Integer> getMinTemp(ArrayList<DailyWeather> dailyWeathers) {
        ArrayList<Integer> minTemps=new ArrayList<>();
        int temp;
        for(int i=0;i<dailyWeathers.size();i++) {
            String temp_str=dailyWeathers.get(i).getTemperature();
            int index=temp_str.indexOf("~");
            temp=Integer.parseInt(temp_str.substring(0,index-1));
            minTemps.add(temp);
            Log.d("WeatherFragment",temp_str);
        }
        return minTemps;
    }
    /**
     * 获取日期
     */
    public ArrayList<String> getDates(ArrayList<DailyWeather> dailyWeathers) {
        ArrayList<String> dates=new ArrayList<>();
        String week;
        for(int i=0;i<dailyWeathers.size();i++) {
            week=dailyWeathers.get(i).getWeek();
            dates.add(week);
            Log.d("WeatherFragment", week);
        }
        return dates;
    }
}
