
package com.wangxuqin.weatherforecast.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.wangxuqin.weatherforecast.entity.City;
import com.wangxuqin.weatherforecast.entity.DailyWeather;
import com.wangxuqin.weatherforecast.entity.TodayWeather;
import com.wangxuqin.weatherforecast.entity.WeatherTile;
import com.wangxuqin.weatherforecast.entity.Weather_idEntity;
import com.wangxuqin.weatherforecast.util.DBHelper;

import java.util.ArrayList;

/**
 * Created by acer on 2016/6/14.
 */

public class DBServer {
    private DBHelper dbhelper;
    public DBServer(Context context) {

        this.dbhelper = new DBHelper(context);

    }

    /**
     * 今日天气数据
     *
     * @param entity
     */

    public void addToday(TodayWeather entity, String cityNo) {

        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[16];
        arrayOfObject[0] = cityNo;
        arrayOfObject[1] = entity.getWind();
        arrayOfObject[2] = entity.getUv_index();
        arrayOfObject[3] = entity.getTravel_index();
        arrayOfObject[4] = entity.getTemperature();
        arrayOfObject[5] = entity.getCity();
        arrayOfObject[6] = entity.getComfort_index();
        arrayOfObject[7] = entity.getDate_y();
        arrayOfObject[8] = entity.getWash_index();
        arrayOfObject[9] = entity.getExercise_index();
        arrayOfObject[10] = entity.getWeather();
        arrayOfObject[11] = entity.getDrying_index();
        arrayOfObject[12] = entity.getWeather_id().getFa();
        arrayOfObject[13] = entity.getDressing_advice();
        arrayOfObject[14] = entity.getWeek();
        arrayOfObject[15] = entity.getDressing_index();
        localSQLiteDatabase.execSQL("insert into today(" +
                "cityNo,wind,uv_index,travel_index," +
                "temperature,city,comfort_index,date_y," +
                "wash_index,exercise_index,weather,drying_index," +
                "fa,dressing_advice,week,dressing_index)" +
                "values(?,?,?, ?,?,?, ?,?,?, ?,?,? ,?,?,? ,? )", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 实时天气数据
     *
     * @param entity
     */

    public void addTile(WeatherTile entity, String cityNo) {

        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[6];
        arrayOfObject[0] = cityNo;
        arrayOfObject[1] = entity.getWind_strength();
        arrayOfObject[2] = entity.getTime();
        arrayOfObject[3] = entity.getHumidity();
        arrayOfObject[4] = entity.getWind_direction();
        arrayOfObject[5] = entity.getTemp();
        localSQLiteDatabase.execSQL("insert into tile(" +
                "cityNo,wind_strength,time,humidity,wind_direction,temp)" +
                "values(?,?,?,?,?,?)", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 添加未来天气数据
     */
    public void addDaily(ArrayList<DailyWeather> entities, String cityNo) {
        Log.d("DBServer",entities.toString());
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        DailyWeather entity;
        for (int i = 0; i < entities.size(); i++) {
            entity = entities.get(i);
            Object[] arrayOfObject = new Object[7];
            arrayOfObject[0] = cityNo;
            arrayOfObject[1] = entity.getWind();
            arrayOfObject[2] = entity.getWeather();
            arrayOfObject[3] = entity.getDate();
            arrayOfObject[4] = entity.getWeek();
            arrayOfObject[5] = entity.getTemperature();
            arrayOfObject[6] = i;
            Log.d("DBServer",entity.getTemperature());

            localSQLiteDatabase.execSQL("insert into daily(" +
                    "cityNo,wind,weather,date,week,temperature,sign)" +
                    "values(?,?,?,?,?,?,?)", arrayOfObject);
            Log.d("DBServer",arrayOfObject[4].toString());
        }
        localSQLiteDatabase.close();
    }

    /**
     * 添加城市数据
     */
    public void addCity(City entity) {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = entity.getId();
        arrayOfObject[1] = entity.getCityName();
        localSQLiteDatabase.execSQL("insert into city(" +
                "cityId,name) values(?,?)", arrayOfObject);
        //Log.d("CityUtilActivity", "验证插入方法是否正确执行-->" + arrayOfObject.toString());
        localSQLiteDatabase.close();
    }

    /**
     * 删除今日天气
     *
     * @param cityNo
     */

    public void deleteToday(String cityNo) {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        //设置了级联删除和级联更新
        //在执行有级联关系的语句的时候必须先设置“PRAGMA foreign_keys=ON”
        //否则级联关系默认失效
        localSQLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = cityNo;
        localSQLiteDatabase.execSQL("delete from today where cityNo=?", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 删除实时天气
     *
     * @param cityNo
     */
    public void deleteTile(String cityNo) {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = cityNo;
        localSQLiteDatabase.execSQL("delete from tile where cityNo=?", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 删除未来天气
     *
     * @param cityNo
     */
    public void deleteDaily(String cityNo) {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = cityNo;
        localSQLiteDatabase.execSQL("delete from daily where cityNo=?", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 删除城市数据
     *
     * @param cityNo
     */
    public void deleteCity(String cityNo) {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = cityNo;
        localSQLiteDatabase.execSQL("delete from city where cityId=?", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 更新今日的天气数据
     *
     * @param cityNo
     */
    public void updateToday(TodayWeather entity, String cityNo) {
        Log.d("DBServer","数据中更新今日天气"+entity.toString());
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[15];
        arrayOfObject[0] = entity.getWind();
        arrayOfObject[1] = entity.getUv_index();
        arrayOfObject[2] = entity.getTravel_index();
        arrayOfObject[3] = entity.getTemperature();
        arrayOfObject[4] = entity.getCity();
        arrayOfObject[5] = entity.getDate_y();
        arrayOfObject[6] = entity.getWash_index();
        arrayOfObject[7] = entity.getExercise_index();
        arrayOfObject[8] = entity.getWeather();
        arrayOfObject[9] = entity.getDrying_index();
        arrayOfObject[10] = entity.getWeather_id().getFa();
        arrayOfObject[11] = entity.getDressing_advice();
        arrayOfObject[12] = entity.getWeek();
        arrayOfObject[13] = entity.getDressing_index();
        arrayOfObject[14] = cityNo;
        localSQLiteDatabase.execSQL("update today " +
                "set wind=?,uv_index=?,travel_index=?," +
                "temperature=?,city=?,comfort_index=?,date_y=?," +
                "wash_index=?,exercise_index=?,weather=?,drying_index=?," +
                "fa=?,dressing_advice=?,week=?,dressing_index=?" +
                " where cityNo=?", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 更新实时天气数据
     */
    public void updateTile(WeatherTile entity, String cityNo) {
        Log.d("DBServer","数据中更新实时天气"+entity.toString());
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[6];
        arrayOfObject[0] = entity.getWind_strength();
        arrayOfObject[1] = entity.getTime();
        arrayOfObject[2] = entity.getHumidity();
        arrayOfObject[3] = entity.getWind_direction();
        arrayOfObject[4] = entity.getTemp();
        arrayOfObject[5] = cityNo;
        localSQLiteDatabase.execSQL("update  tile " +
                "set wind_strength=?,time=?,humidity=?,wind_direction=?,temp=? " +
                "where cityNo=? ", arrayOfObject);
        localSQLiteDatabase.close();
    }

    /**
     * 更新未来天气数据
     */
    public void updateDaily(ArrayList<DailyWeather> entities, String cityNo) {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        DailyWeather entity;
        for (int i = 0; i < entities.size(); i++) {
            entity = entities.get(i);
            Object[] arrayOfObject = new Object[7];
            arrayOfObject[0] = entity.getWind();
            arrayOfObject[1] = entity.getWeather();
            arrayOfObject[2] = entity.getDate();
            arrayOfObject[3] = entity.getWeek();
            arrayOfObject[4] = entity.getTemperature();
            arrayOfObject[5] = cityNo;
            arrayOfObject[6] = i;
            localSQLiteDatabase.execSQL("update daily " +
                    "set wind=?,weather=?,date=?,week=?,temperature=? " +
                    "where cityNo=? and sign=?", arrayOfObject);
        }
        localSQLiteDatabase.close();
    }

    /**
     * 使用城市编号查找今日天气
     *
     * @param cityNo
     * @return
     */
    public TodayWeather findTodayById(String cityNo) {
        TodayWeather today = new TodayWeather();
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getReadableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select " +
                "wind,uv_index,travel_index,temperature,city,comfort_index,date_y," +
                "wash_index,exercise_index,weather,drying_index,fa,dressing_advice," +
                "week,dressing_index from today  " +
                "where cityNo=?  order by week desc", new String[]{cityNo});
        if (localCursor.moveToFirst()) {
            today.setWind(localCursor.getString(localCursor.getColumnIndex("wind")));
            today.setUv_index(localCursor.getString(localCursor.getColumnIndex("uv_index")));
            today.setTravel_index(localCursor.getString(localCursor.getColumnIndex("travel_index")));
            today.setTemperature(localCursor.getString(localCursor.getColumnIndex("temperature")));
            today.setCity(localCursor.getString(localCursor.getColumnIndex("city")));
            today.setComfort_index(localCursor.getString(localCursor.getColumnIndex("comfort_index")));
            today.setDate_y(localCursor.getString(localCursor.getColumnIndex("date_y")));
            today.setWash_index(localCursor.getString(localCursor.getColumnIndex("wash_index")));
            today.setExercise_index(localCursor.getString(localCursor.getColumnIndex("exercise_index")));
            today.setWeather(localCursor.getString(localCursor.getColumnIndex("weather")));
            today.setDrying_index(localCursor.getString(localCursor.getColumnIndex("drying_index")));
            Weather_idEntity wid=new Weather_idEntity();
            wid.setFa(localCursor.getString(localCursor.getColumnIndex("fa")));
            today.setWeather_id(wid);
            today.setDressing_advice(localCursor.getString(localCursor.getColumnIndex("dressing_advice")));
            today.setWeek(localCursor.getString(localCursor.getColumnIndex("week")));
            today.setDressing_index(localCursor.getString(localCursor.getColumnIndex("dressing_index")));
        }
        localSQLiteDatabase.close();
        return today;
    }

    /**
     * 使用城市编号查找今日天气
     *
     * @param cityNo
     * @return
     */
    public WeatherTile findTileById(String cityNo) {
        WeatherTile tile = new WeatherTile();
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getReadableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select " +
                "wind_strength,time,humidity,wind_direction,temp from tile  " +
                "where cityNo=?", new String[]{cityNo});
        if (localCursor.moveToNext()) {
            tile.setWind_strength(localCursor.getString(localCursor.getColumnIndex("wind_strength")));
            tile.setTime(localCursor.getString(localCursor.getColumnIndex("time")));
            tile.setHumidity(localCursor.getString(localCursor.getColumnIndex("humidity")));
            tile.setWind_direction(localCursor.getString(localCursor.getColumnIndex("wind_direction")));
            tile.setTemp(localCursor.getString(localCursor.getColumnIndex("temp")));
        }
        localSQLiteDatabase.close();
        return tile;
    }

    /**
     * 根据城市编号查找未来天气
     */
    public ArrayList<DailyWeather> findDailysById(String cityNo) {
        DailyWeather daily;
        ArrayList<DailyWeather> dailyWeathers = new ArrayList<>();
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getReadableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select  " +
                "wind,weather,date,week,temperature from daily " +
                " where cityNo=?", new String[]{cityNo});
        while (localCursor.moveToNext()) {
            daily= new DailyWeather();
            daily.setWind(localCursor.getString(localCursor.getColumnIndex("wind")));
            daily.setWeather(localCursor.getString(localCursor.getColumnIndex("weather")));
            daily.setDate(localCursor.getString(localCursor.getColumnIndex("date")));
            daily.setWeek(localCursor.getString(localCursor.getColumnIndex("week")));
            daily.setTemperature(localCursor.getString(localCursor.getColumnIndex("temperature")));
            dailyWeathers.add(daily);
        }
        /*Log.d("DBServer","正在讲查询到的数据列表输出");
        for(int i=0;i<dailyWeathers.size();i++) {
            Log.d("DBServer","第"+i+"条"+dailyWeathers.get(i).getWeek());
        }*/
        localSQLiteDatabase.close();
        return dailyWeathers;
    }
    /**
     * 查询城市列表
     */
    public ArrayList<City> findCities() {
        City city;
        ArrayList<City> cityList = new ArrayList<>();
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getReadableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select cityId,name from city", null);
        while(localCursor.moveToNext()) {
            city = new City();
            city.setId(localCursor.getString(localCursor.getColumnIndex("cityId")));
            city.setCityName(localCursor.getString(localCursor.getColumnIndex("name")));
            cityList.add(city);
            //Log.d("CityUtilActivity", "查询到的：cityId:" + city.getId() + "\name:" + city.getCityName());
        }
        localSQLiteDatabase.close();
        return cityList;
    }
    /**
     * 判断城市是否存在
     */
    public boolean isCityExists(String cityNo)
    {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select count(*)  from city  " +
                "where cityId=?", null);
        localCursor.moveToFirst();
        if(localCursor.getLong(0)>0)
            return true;
        else
            return false;
    }

    /**
     * 判断城市的天气记录是否存在
     */
    public boolean isWeatherExists(String cityNo)
    {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select count(*)  from tile  " +
                "where cityNo=?", new String[]{cityNo});
        localCursor.moveToFirst();
        if(localCursor.getLong(0)>0)
            return true;
        else
            return false;
    }
}
