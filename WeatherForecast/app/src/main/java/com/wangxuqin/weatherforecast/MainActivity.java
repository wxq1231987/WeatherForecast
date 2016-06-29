package com.wangxuqin.weatherforecast;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.wangxuqin.weatherforecast.CityUtilActivity;
import com.wangxuqin.weatherforecast.FileUtil;
import com.wangxuqin.weatherforecast.adapter.*;
import com.wangxuqin.weatherforecast.entity.*;
import com.wangxuqin.weatherforecast.ui.WeatherFragment;
import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.wangxuqin.weatherforecast.*;
import com.wangxuqin.weatherforecast.util.DBServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private Context context;
    private StringBuffer tv = new StringBuffer();
    private final int DATA = 1;
    private WeatherInfo info;
    //数据源
    private WeatherTile weatherTile;
    private ArrayList<DailyWeather> dailyWeathers;
    private TodayWeather todayWeather;
    private ArrayList<City> cityList=new ArrayList<>();
    private int first = 1;
    //声明抽屉中的控件
    @Bind(R.id.city_lv)
    ListView city_lv;
    @Bind(R.id.meg_tv)
    TextView meg_tv;
    @Bind(R.id.add_imgBtn)
    ImageButton add_imgBtn;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.left_drawer)
    RelativeLayout left_drawer;
    //Toolbar
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private CityListAdapter cityAdapter;
    //关键字
    private static final int  REQUEST_CODE_CITY=0;
    private static final int  RESPONSE_SUCC_CITY=1;
    private static final int  RESPONSE_FIAL_CITY=2;
    private static final String LAST_REQUEST_TIME="TIME";
    //监听器
    DrawerItemClickListener drawerItemListener;
    //当前显示的城市位置
    int loc;
    //数据库
    DBServer dbServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        dbServer=new DBServer(context);
        drawerItemListener=new DrawerItemClickListener();
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("城市天气");//R.drawable.navi
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                loadCityList();
                //抽屉关闭ListView恢复默认的样式
                cityAdapter.setMode(1);
            }
        };
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        loadCityList();
        initDrawerView();
        if(cityList.size()!=0) {
            initFragment(0);
        }
    }

    /*
    *从网络中根据城市名称获取天气数据
     */
    public void getWeatherData(Context context,String cityName) {
        try {
            Log.d("today","请求网络数据的城市："+cityName);
            Parameters params = new Parameters();
            params.add("cityname", URLEncoder.encode(cityName, "UTF-8"));
            params.add("dtype", "json");
            params.add("format", 2);
            params.add("key", "60716d5707421228c22f06eb5fb45e17");
            JuheData.executeWithAPI(context,
                    39,
                    "http://v.juhe.cn/weather/index",
                    JuheData.GET, params, wCallBack);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化视图
     */
    private void initDrawerView() {
        city_lv = (ListView) findViewById(R.id.city_lv);
        city_lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        cityAdapter = new CityListAdapter(cityList, this);
        cityAdapter.setData(cityList);
        city_lv.setAdapter(cityAdapter);
        //设置导航抽屉列表的点击事件
        city_lv.setOnItemClickListener(drawerItemListener);
        //ListView的长按事件
        city_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //长按显示删除按钮
                Log.d("ListView", "长按事件已经触发");
                cityAdapter.setMode(2);
                first = 1;//第一次点击
                return false;
            }
        });
        /**
         * 当点击添加按钮，进入城市选择
         */
        add_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭抽屉
                drawerLayout.closeDrawer(left_drawer);
                //从网络当中请求城市数据
                requestCity(cityList);
            }
        });
    }

    /**
     * 开启CityUtilActivity,添加城市
     */
    public void requestCity(ArrayList<City> cityList) {
        Intent intent = new Intent(MainActivity.this, CityUtilActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cityList", cityList);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_CITY);
    }
    /**
     * 从数据库中加载城市数据
     */
    protected void loadCityList() {
        ArrayList<City> cities= dbServer.findCities();

        //如果城市列表为空，添加城市数据
        if(cities==null||cities.size()==0) {
            requestCity(cityList);
        } else if(cities.size()==cityList.size()){
            //城市列表不为空
            cityList=cities;
        } else {
            //数据库中的城市列表发生变化
            cityList=cities;
            initFragment(0);
        }
    }
    /**
     * 从数据库中加载天气数据
     *//*
    protected void loadWeatherData() {
        Log.d("Weather", "loadWeatherData");
        todayWeather=dbServer.findTodayById()
        ArrayList<City> cities= dbServer.findCities();
        //如果城市列表为空，添加城市数据
        if(cities==null||cities.size()==0) {
            requestCity(cityList);
        } else{
            //城市列表不为空
            cityList=cities;
        }
    }*/
    /**
     * 当城市选择Activity返回时候调用
     */
    @Override
    protected void onActivityResult(int requestCode,int responseCode,Intent intent) {
        super.onActivityResult(requestCode,responseCode,intent);
        /**
         *如果返回码是1，则请求成功
         */
        if(requestCode==0&&responseCode==1) {
            Log.d("MainActivity","验证CitiUtilActivity返回以后数据的状态--->");
            Bundle bundle=intent.getExtras();
            //取出intent中的城市数据，并保存
            ArrayList<City> cityList=(ArrayList<City>)bundle.getSerializable("cityList");
            //FileUtil.saveDrawerData(cityList, context);
            loadCityList();
            cityAdapter.setData(cityList);
            //加载最新城市的天气数据
            int size=cityList.size();
            City city=cityList.get(size-1);
            loc=size-1;
            context=getApplicationContext();
            getWeatherData(context, city.getCityName());
        }
    }
    /**
     * 回调接口，处理天气数据
     */
    DataCallBack wCallBack = new DataCallBack() {
        /**
         * 请求成功时调用的方法 statusCode为http状态码,responseString    *为请求返回数据.
         */
        @Override
        public void onSuccess(int statusCode, String responseString) {
            context=null;
            tv.append(responseString);
            Date now =  new  Date();
            SimpleDateFormat   dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期式
            String  str= dateFormat.format(now);
            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString(cityList.get(loc).getCityName(),str)
                    .commit();
            /**
             * 将数据返回时进行解析
             */
            try {
                weatherTile=null;
                todayWeather=null;
                dailyWeathers=null;
                Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_LONG).show();
                JSONObject data = new JSONObject(tv.toString());
                /**
                 * 此处解析JSON数据
                 */
                //WeatherInfo天气数据
                JSONObject resultEntity = data.getJSONObject("result");
                //WeatherTile实时天气SK
                JSONObject sk = resultEntity.getJSONObject("sk");
                //TodayWeahter今日天气today
                JSONObject today = resultEntity.getJSONObject("today");
                JSONObject wid=today.getJSONObject("weather_id");
                //DailyWeather未来天气future
                JSONArray futureArray = resultEntity.getJSONArray("future");
                //测试数据
                //实时天气
                weatherTile =
                        new WeatherTile(
                                sk.getString("wind_strength"),
                                sk.getString("time"),
                                sk.getString("humidity"),
                                sk.getString("wind_direction"),
                                sk.getString("temp"));
                //今日天气
                todayWeather =
                        new TodayWeather(today.getString("wind"),
                                today.getString("uv_index"),
                                today.getString("travel_index"),
                                today.getString("temperature"),
                                today.getString("city"),
                                today.getString("comfort_index"),
                                today.getString("date_y"),
                                today.getString("wash_index"),
                                today.getString("exercise_index"),
                                today.getString("weather"),
                                today.getString("drying_index"),
                                new Weather_idEntity(wid.getString("fa"),wid.getString("fb")),
                                today.getString("dressing_advice"),
                                today.getString("week"),
                                today.getString("dressing_index"));
                //未来天气
                dailyWeathers =
                        new ArrayList<>();
                for (int i = 0; i < futureArray.length(); i++) {
                    JSONObject obj = (JSONObject) futureArray.get(i);
                    DailyWeather dailyWeather =
                            new DailyWeather(obj.getString("wind"),
                                    obj.getString("weather"),
                                    obj.getString("date"),
                                    obj.getString("week"),
                                    obj.getString("temperature"));
                    dailyWeathers.add(dailyWeather);
                    Log.d("daily",dailyWeather.toString());
                }

                /**
                 * 数据库操作
                 */
                 String cityName=cityList.get(loc).getCityName();
                String city=todayWeather.getCity().concat("市");
                Log.d("today","请求的是"+cityList.get(loc).getCityName()+"城市的天气");
                Log.d("t oday","网络中返回的是"+todayWeather.getCity()+"中的天气");
                if(city.equals(cityName)) {
                    Log.d("today","验证是否执行到");
                    if(dbServer.isWeatherExists(cityList.get(loc).getId())) {
                        //天气记录存在，更新数据
                        Log.d("today","城市名"+cityList.get(loc).getCityName());
                        Log.d("today","城市今日天气"+todayWeather.toString());
                        dbServer.updateToday(todayWeather, cityList.get(loc).getId());
                        dbServer.updateTile(weatherTile, cityList.get(loc).getId());
                        dbServer.updateDaily(dailyWeathers,cityList.get(loc).getId());
                    } else {
                        Log.d("MainActivity","写入数据库数据->"+cityList.get(loc).getCityName());
                        //第一次写入，直接添加
                        dbServer.addToday(todayWeather,cityList.get(loc).getId());
                        dbServer.addTile(weatherTile,cityList.get(loc).getId());
                        dbServer.addDaily(dailyWeathers,cityList.get(loc).getId());
                    }
                    initFragment(loc);
                }
            } catch (JSONException e) {
                Log.d("Success", e.getMessage());
                e.printStackTrace();
            }

        }
        /**
         * 请求完成时调用的方法,无论成功或者失败都会调用.
         */
        @Override
        public void onFinish() {
        }
        @Override
        public void onFailure(int statusCode, String responseString, Throwable throwable) {
            // TODO Auto-generated method stub
            tv.append(throwable.getMessage() + "\n");
            Toast.makeText(MainActivity.this, "请检查网络连接", Toast.LENGTH_LONG).show();
            Log.d("loadSQLite", "从数据库中加载数据");
            /**
             * 如果失败，从数据库中加载数据
             */
            todayWeather=dbServer.findTodayById(cityList.get(loc).getId());
            weatherTile=dbServer.findTileById(cityList.get(loc).getId());
            dailyWeathers=dbServer.findDailysById(cityList.get(loc).getId());
        }
    };

    /**
     * Created by acer on 2016/6/5.
     * ListView的单击事件
     */
    class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //若ListView处于单选模式
            if (cityAdapter.getMode() == 2 && first != 1) {
                Log.d("ListView", "单击事件，模式为2");
                cityAdapter.setMode(1);
                //退出删除模式，保存当前的城市列表
                FileUtil.saveDrawerData(cityAdapter.getData(),MainActivity.this);
                selectItem(position);
            } else if (cityAdapter.getMode() == 1) {
                Log.d("cityAdapter",String.valueOf(position));
                selectItem(position);
            }
            first++;
        }
        public void selectItem(int position) {
            loc=position;
            if(cityList.size()!=0) {
                initFragment(loc);
            }
            /**
             * 当上次请求数据的时间离现在的时间过去15分钟，进行刷新
             */
            String str=getPreferences(MODE_PRIVATE)
                    .getString(cityList.get(loc).getCityName(),"2016-06-14 00:00:00");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now=new Date();
            Date last=new Date();
            try{
                last = format.parse(str);
            } catch(ParseException e) {
                e.printStackTrace();
            }
            context=getApplicationContext();
            //getWeatherData(context, cityList.get(loc).getCityName());
             if((now.getTime()-last.getTime())/1000>15*60) {
                 getWeatherData(context, cityList.get(loc).getCityName());
             }
            drawerLayout.closeDrawer(left_drawer);
        }

    };

    /**
     * 初始化Fragment
     */
    public  void initFragment(int loc) {
        Log.d("MainActivity", "验证数据库的数据是否存在->布尔值:" + String.valueOf(dbServer.isWeatherExists(cityList.get(loc).getId())));
       /* todayWeather=null;
        weatherTile=null;
        dailyWeathers=null;*/
        TodayWeather today=null;
        WeatherTile tile=null;
        ArrayList<DailyWeather> dailys=null;
        if (dbServer.isWeatherExists(cityList.get(loc).getId())) {

            today=dbServer.findTodayById(cityList.get(loc).getId());
            tile=dbServer.findTileById(cityList.get(loc).getId());
            dailys=dbServer.findDailysById(cityList.get(loc).getId());
        }
        //+Log.d("daily_main",dailyWeathers.toString());
        //Log.d("Tile","从数据库中读取到的"+weatherTile.toString());
        Fragment fragment = WeatherFragment.getInstance(context,
                cityList.get(loc),tile,today,dailys);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        city_lv.setItemChecked(loc, true);
    }
    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_item, menu);
        return true;
    }
    //预处理选项菜单
    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        return super.onPrepareOptionsMenu(menu);
    }
    //选中菜单项
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        Log.d("AddCity", "onOptionsItemSelected");
        if (R.id.action_share == item.getItemId()) {
            Log.d("Share", "分享");
        }
        return true;
    }
    //关闭选项菜单
    @Override
    public void onOptionsMenuClosed (Menu menu){
        super.onOptionsMenuClosed(menu);
    }




}



