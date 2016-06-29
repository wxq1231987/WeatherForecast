package com.wangxuqin.weatherforecast.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wangxuqin.weatherforecast.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/6/13.
 */
public class TempUI {
    ArrayList<String> dates;
    ArrayList<Integer> temps;
    ArrayList<Integer> temps1;
    LineChart xLineChart;
    LineChart nLineChart;
    public TempUI(ArrayList<String> dates,
                  ArrayList<Integer> temps,
                  ArrayList<Integer> temps1,
                  LineChart xLineChart,
                  LineChart nLineChart) {
        this.dates=dates;
        this.temps=temps;
        this.temps1=temps1;
        this.xLineChart=xLineChart;
        this.nLineChart=nLineChart;
        if(dates==null) {
            dates=new ArrayList<>();
            temps=new ArrayList<>();
            temps1=new ArrayList<>();
            dates.add("星期一");
            dates.add("星期二");
            dates.add("星期三");
            dates.add("星期四");
            dates.add("星期五");
            temps.add(22);
            temps.add(26);
            temps.add(27);
            temps.add(28);
            temps.add(25);
            temps1.add(15);
            temps1.add(16);
            temps1.add(20);
            temps1.add(18);
            temps1.add(15);
        }

        LineData xLineData = getLineData(dates, temps);
        LineData    nLineData = getLineData(dates, temps1);
        showMaxChart(xLineChart, xLineData, Color.rgb(255, 255, 255));
        showMinChart(nLineChart, nLineData, Color.rgb(255, 255, 255));
    }
    // 设置最高温度显示的样式
    private void showMaxChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放
        //if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//
        lineChart.setBackgroundColor(color);// 设置背景
        lineChart.setData(lineData); // 设置数据
        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        lineChart.getXAxis().setEnabled(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
        lineChart.getXAxis().setDrawLabels(false); //是否显示X坐标轴上的刻度，默认是true
        //xAxis.setTextColor(Color.rgb(145, 13, 64)); //X轴上的刻度的颜色
        //xAxis.setTextSize(5); //X轴上的刻度的字的大小 单位dp
        //xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
        //xAxis.setGridColor(Color.rgb(145, 13, 64)); //X轴上的刻度竖线的颜色
        //xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        //xAxis.enableGridDashedLine(40, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        lineChart.getAxisLeft().setAxisMaxValue(40);// Y坐标轴最大值
        lineChart.getAxisLeft().setAxisMinValue(-5);// Y坐标轴最小值
        lineChart.getAxisLeft().setEnabled(false);//隐藏左边的坐标轴
        lineChart.getAxisRight().setEnabled(false); // 隐藏右边的坐标轴
        lineChart.getAxisRight().setEnabled(false);
        //隐藏左边坐标轴横网格线
        lineChart.getAxisLeft().setDrawGridLines(false);
        //隐藏右边坐标轴横网格线
        lineChart.getAxisRight().setDrawGridLines(false);
        //隐藏X轴竖网格线
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // 让x轴在下面\
        // 设置在Y轴上是否是从0开始显示
        lineChart.setBorderColor(R.color.transparent);
        //是否在Y轴显示数据，就是曲线上的数据
        //lineChart.getXAxis().setGridColor(getResources().getColor(R.color.transparent));
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(10f);// 字体
        mLegend.setTextColor(Color.rgb(144, 188, 223));// 颜色
        //mLegend.setTypeface(mTf);// 字体
        lineChart.animateX(2500); // 立即执行的动画,x轴
    }
    // 设置最高温度显示的样式
    private void showMinChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放
        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//
        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据
        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        /*xAxis.setEnabled(false);     //是否显示X坐标轴 及 对应的刻度竖线，默认是true
        xAxis.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxis.setDrawGridLines(false); //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true*/

        //xAxis.setTextColor(Color.rgb(145, 13, 64)); //X轴上的刻度的颜色
        //xAxis.setTextSize(5); //X轴上的刻度的字的大小 单位dp
//      xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
        //xAxis.setGridColor(Color.rgb(145, 13, 64)); //X轴上的刻度竖线的颜色
        //xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        //xAxis.enableGridDashedLine(40, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        lineChart.getAxisLeft().setAxisMaxValue(30);// Y坐标轴最大值
        lineChart.getAxisLeft().setAxisMinValue(-20);// Y坐标轴最小值
        lineChart.getAxisLeft().setEnabled(false);//隐藏左边的坐标轴
        lineChart.getAxisRight().setEnabled(false); // 隐藏右边的坐标轴
        lineChart.getAxisRight().setEnabled(false);
        //隐藏左边坐标轴横网格线
        lineChart.getAxisLeft().setDrawGridLines(false);
        //隐藏右边坐标轴横网格线
        lineChart.getAxisRight().setDrawGridLines(false);
        //隐藏X轴竖网格线
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // 让x轴在下面\
        // 设置在Y轴上是否是从0开始显示
        lineChart.setBorderColor(R.color.transparent);
        //是否在Y轴显示数据，就是曲线上的数据
        //lineChart.getXAxis().setGridColor(
          //      getResources().getColor(R.color.transparent));
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(10);// 字体
        mLegend.setTextColor(Color.rgb(144,188,223));// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(2500); // 立即执行的动画,x轴
    }
    /**
     * 生成一个数据
     * @param dates 表示图表中的日期
     * @param temps 表示图表中温度
     * @return
     */
    private LineData getLineData(ArrayList<String> dates,ArrayList<Integer> temps) {
        // x轴的数据
        ArrayList<String> xValues = new ArrayList<>();
        // y轴的数据
        ArrayList<Entry> yValues= new ArrayList<>();
        for (int    i = 0; i< dates.size(); i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(dates.get(i));
        }
        for (int  i = 0; i< dates.size(); i++) {
            float y=(float)temps.get(i);
            yValues.add(new Entry(temps.get(i),i));
        }

        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "" /*显示在比例图上*/);
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v) {
                int n=(int)v;
                String str=String.valueOf(n);
                return str+"℃";
            }
        });
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(3.00f); // 线宽
        lineDataSet.setCircleSize(4f);// 显示的圆形大小
        lineDataSet.setColor(Color.rgb(114,188,223));// 设置折线的显示颜色
        lineDataSet.setCircleColor(Color.rgb(114,188,223));// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色

        ArrayList<LineDataSet>lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData    lineData = new LineData(xValues, lineDataSets);

        return  lineData;
    }
}




