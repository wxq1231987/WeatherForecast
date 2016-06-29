package com.wangxuqin.weatherforecast;

import android.content.Context;
import android.os.Environment;

import com.wangxuqin.weatherforecast.entity.*;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {
    private String SDPATH;

    public String getSDPATH() {
        return SDPATH;
    }

    //构造函数，得到SD卡的目录，这行函数得到的目录名其实是叫"/SDCARD"
    public FileUtil() {
        SDPATH = Environment.getExternalStorageDirectory() + "/";
    }

    //在SD卡上创建文件
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }

    //在SD卡上创建目录
    public File createSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        dir.mkdir();
        return dir;
    }

    //判断SD卡上的文件夹是否存在
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    //将一个InputStream里面的数据写入到SD卡中
    //将input写到path这个目录中的fileName文件上
    public File write2SDFromInput(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            createSDDir(path);
            file = createSDFile(path + fileName);
            //FileInputStream是读取数据，FileOutputStream是写入数据，写入到file这个文件上去
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((input.read(buffer)) != -1) {
                output.write(buffer);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 存储天气数据数据
     */
    public void saveWeatherData() {

    }
    /**
     * 加载城市数据,从内部存储中获取数据
     */
    public static ArrayList<City> loadDrawerData(Context context) {
        ArrayList<City> cityList = new ArrayList<>();
        String data="cityList";
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=context.openFileInput(data);
            ois = new ObjectInputStream(fis);
            cityList=(ArrayList<City>)ois.readObject();
        }catch(FileNotFoundException e){
            return cityList;
            //e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                if(ois!=null) {
                    ois.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return cityList;
    }
    /**
     * 保存城市数据,保存到内部存储
     */
    public static void saveDrawerData(ArrayList<City> cityList,Context context) {
        String data="cityList";
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{
            fos=context.openFileOutput("cityList", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(cityList);
            oos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                oos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 加载城市数据,从内部存储中获取数据
     */
    public static String[] loadRegionData(Context context) {
        String[] regions = new String[]{};
        String data="regionList";
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=context.openFileInput(data);
            ois = new ObjectInputStream(fis);
            regions=(String[])ois.readObject();
        }catch(FileNotFoundException e){
            return regions;
            //e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                if(ois!=null) {
                    ois.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return regions;
    }
    /**
     * 保存省、直辖市、市的数据
     */
    public static void saveRegionData(String regions[],Context context) {
        String data="regionList";
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{
            fos=context.openFileOutput(data, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(regions);
            oos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                oos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 加载城市数据,从内部存储中获取数据
     */
    public static String[] loadIdData(Context context) {
        String[] ids = new String[]{};
        String data="idList";
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=context.openFileInput(data);
            ois = new ObjectInputStream(fis);
            ids=(String[])ois.readObject();
        }catch(FileNotFoundException e){
            return ids;
            //e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                if(ois!=null) {
                    ois.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return ids;
    }
    /**
     * 保存省、直辖市、市的数据
     */
    public static void saveIdData(String ids[],Context context) {
        String data="idList";
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{
            fos=context.openFileOutput(data, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ids);
            oos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                oos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 保存城市天气
     */
    public static void saveWeatherData(Object weather[],Context context) {
        String data="weather";
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{
            fos=context.openFileOutput(data, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(weather);
            oos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                oos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 加载城市数据,从内部存储中获取数据
     */
    public static Object[] loadWeatherData(Context context) {
        Object[] weathers = new Object[]{};
        String data="weather";
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=context.openFileInput(data);
            ois = new ObjectInputStream(fis);
            weathers=(Object[])ois.readObject();
        }catch(FileNotFoundException e){
            return weathers;
            //e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                if(ois!=null) {
                    ois.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return weathers;
    }
}