package com.wangxuqin.weatherforecast.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.wangxuqin.weatherforecast.R;
import com.squareup.picasso.Picasso;
import com.wangxuqin.weatherforecast.entity.*;
import com.wangxuqin.weatherforecast.util.DBServer;

import java.util.ArrayList;

/**
 * Created by acer on 2016/6/5.
 */
public class CityListAdapter extends BaseAdapter {
    private int mode;
    private ImageButtonListener listener;
    private ArrayList<City> cityList;
    private Context context;
    private LayoutInflater inflater;
    private DBServer dbServer;
    public CityListAdapter(ArrayList<City> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
        dbServer=new DBServer(context);
        inflater= LayoutInflater.from(context);
        mode=1;//默认情况下为单选模式
    }
    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public City getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null==convertView) {
            convertView=inflater.inflate(R.layout.item_city,parent,false);
            holder=new ViewHolder();
            listener=new ImageButtonListener();
            holder.logo=(ImageView)convertView.findViewById(R.id.logo_iv);
            holder.name=(TextView)convertView.findViewById(R.id.city_tv);
            holder.del=(ImageButton)convertView.findViewById(R.id.del_imgBtn);
            holder.del.setOnClickListener(listener);
            holder.del.setTag(listener);
            holder.del.setVisibility(View.INVISIBLE);
            convertView.setTag(holder);
        } else{
            holder=(ViewHolder)convertView.getTag();
            listener=(ImageButtonListener)holder.del.getTag();
        }
        //如果为删除模式，设置监听器,激活ImageButton
        if(mode==2) {
            holder.del.setVisibility(View.VISIBLE);
        } else {
            holder.del.setVisibility(View.INVISIBLE);
            holder.del.setFocusable(false);
        }
        /**
         * 加载数据
         */
        City city=cityList.get(position);
        holder.logo.setImageResource(R.drawable.logo);
        holder.name.setText(city.getCityName());
        holder.del.setImageResource(R.drawable.del);
        listener.setData(city);//不能忘记
        /*String url="http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%95%BF%E6%B2%99&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1455908031,2564616819&os=2155695707,459479081&simid=0,0&pn=2&rn=1&di=14279783460&ln=1000&fr=&fmq=1465269314623_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=2&ist=&jit=&bdtype=11&gsm=0&objurl=http%3A%2F%2Fi4.qhimg.com%2Ft0154c7939742a7f6e6.jpg&rpstart=0&rpnum=0&ctd=1465269323806^3_1349X635%1";
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
                .centerInside()
                .into(holder.logo);*/
        return convertView;
    }
    public void setMode(int mode) {
        this.mode=mode;
        notifyDataSetChanged();
    }
    public int getMode() {
        return mode;
    }
    /**
     * 内部类ViewHolder
     */
    class ViewHolder {
        private ImageView logo;
        private TextView name;
        private ImageButton del;

        public ViewHolder() {

        }
    }
    /**
     * ImageButton的监听器
     */
    protected class ImageButtonListener implements View.OnClickListener{
        private City data;
        public void setData(City city) {
            data=city;
        }
        public City getData() {
            return data;
        }

        @Override
        public void onClick(View v) {
            if(mode==2) {
                City city=getData();
                cityList.remove(city);
                dbServer.deleteCity(city.getId());
                dbServer.deleteToday(city.getId());
                dbServer.deleteTile(city.getId());
                dbServer.deleteDaily(city.getId());
                boolean result=dbServer.isCityExists(city.getId());
                Log.d("CityListAdapter","验证数据库中的对应城市是否删除"+String.valueOf(!result));
                boolean result1=dbServer.isWeatherExists(city.getId());
                Log.d("CityListAdapter","验证数据库中的对应天气是否删除"+String.valueOf(!result1));
                notifyDataSetChanged();
            }
            return;
        }
    }
    /**
     * 设置数据源
     */
    public void setData(ArrayList<City> cityList) {
        this.cityList=cityList;
        notifyDataSetChanged();
    }
    /**
     * 获取数据源
     */
    public ArrayList<City> getData() {
        return cityList;
    }
}

