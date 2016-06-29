package com.wangxuqin.weatherforecast.util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{

  public DBHelper(Context context) {

      super(context, "weather.db", null, 4);
  }

  @Override
  public void onCreate(SQLiteDatabase db)
  {
      Log.d("my1", "create table table:");
      String citySQL="create table city("+
              "cityId varchar(50)," +
              "name varchar(16))";
    String todaySQL="create table today("+
          "cityNo varchar(50),"+
          "wind varchar(16),"+
          "uv_index varchar(16),"+
          "travel_index varchar(16),"+
          "temperature varchar(16),"+
          "city varchar(16),"+
          "comfort_index varchar(16),"+
          "date_y varchar(16),"+
          "wash_index varchar(16),"+
          "exercise_index varchar(16),"+
          "weather varchar(16),"+
          "drying_index varchar(16),"+
          "fa varchar(6),"+
          "dressing_advice varchar(16),"+
          "week varchar(16),"+
          "dressing_index varchar(16))";

    String tileSQL="create table tile("+
          "cityNo varchar(50),"+
          "wind_strength varchar(16),"+
          "time varchar(16),"+
          "humidity varchar(16),"+
          "wind_direction varchar(16),"+
          "temp varchar(16))";

    String dailySQL="create table daily("+
          "cityNo varchar(50),"+
          "wind varchar(16),"+
          "weather varchar(16),"+
          "date varchar(16),"+
          "week varchar(16),"+
          "temperature varchar(16),"+
            "sign integer)";

    db.execSQL(todaySQL);
    db.execSQL(tileSQL);
    db.execSQL(dailySQL);
    db.execSQL(citySQL);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {

  }

}

