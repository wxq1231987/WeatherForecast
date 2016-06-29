package com.wangxuqin.weatherforecast;

import android.util.Log;

import com.wangxuqin.weatherforecast.FileUtil;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherDataUtil {
	public static String FILE_NAME="data.txt";
	public void getWeatherData(String address,String cityName) throws Exception {
		InputStream in;
		FileOutputStream fos;
		OutputStreamWriter osw;
		String code;
		Log.d("WeatherDataUtil", "开始获取数据");
		try {
			URL url = new URL(address + cityName);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setRequestProperty("Content-Type", "application/json");
			httpUrlConnection.setRequestProperty("Charset", "utf-8");
			httpUrlConnection.setRequestProperty("Accept-Charset", "UTF-8");
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("accept", "application/json;charset=utf-8");
			httpUrlConnection.setRequestProperty("connection", "Keep-Alive");
			httpUrlConnection.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
			urlConnection.setConnectTimeout(5000);
			httpUrlConnection.connect();
			Log.d("WeatherDataUtil", httpUrlConnection.toString());
			int len;
			in = httpUrlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			code = "UTF-8";
			/**
			 * 此处判断外部存储器是否存在
			 */
			FileUtil fu=new FileUtil();
			fu.createSDDir("/Weather/");
			File file=fu.createSDFile("data.txt");
			Log.d("WeatherDataUtil", file.getAbsolutePath());
			Log.d("WeatherDataUtil", String.valueOf(fu.isFileExist("data.txt")));
			try {
				//Log.d("WeatherDataUtil",context.toString());
				fos = new FileOutputStream(file);
				osw = new OutputStreamWriter(fos, code);
				Log.d("WeatherDataUtil", "开始请求数据");
				if (httpUrlConnection.getResponseCode() == 200) {
					Log.d("WeatherDataUtil", "正在传输数据");
					char[] buffer = new char[1024];
					while ((len = br.read(buffer)) != -1) {
						Log.d("WeatherDataUtil", new String(buffer, 0, len));
						osw.write(buffer, 0, len);
					}
					osw.flush();
					osw.close();
					fos.close();
					httpUrlConnection.disconnect();
					Log.d("WeatherDataUtil", "数据接收完毕");
				} else {
					throw new Exception("访问网络失败！");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
