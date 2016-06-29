package com.wangxuqin.weatherforecast.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangxuqin.weatherforecast.R;
import com.wangxuqin.weatherforecast.entity.*;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/6/10.
 */
public class RegionAdapter extends BaseAdapter {
    private ArrayList<Region> regionList;
    private Context context;
    private LayoutInflater inflater;
    public RegionAdapter(ArrayList<Region> regionList,Context context) {
        this.regionList=regionList;
        this.context=context;
        inflater= LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return regionList==null?0:regionList.size();
    }

    @Override
    public Region getItem(int position) {
        return regionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.region_check, parent, false);
            holder = new ViewHolder();
            holder.cityCheck_tv=(TextView)convertView.findViewById(R.id.cityCheck_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * 加载数据
         */
        Region region = regionList.get(position);
        holder.cityCheck_tv.setText(region.getName());

        return convertView;
    }

    /**
     * 设置数据源
     * @param regionList 数据
     */
    public void setData(ArrayList<Region> regionList) {
        this.regionList=regionList;
        notifyDataSetChanged();
    }

    /**
     * 获取数据源
     * @return 数据
     */
    public ArrayList<Region> getData() {
        return regionList;
    }
    /**
     * 内部类ViewHolder
     */
    class ViewHolder {
        TextView cityCheck_tv;
    }
}

