package com.wangxuqin.weatherforecast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.wangxuqin.weatherforecast.R;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.wangxuqin.weatherforecast.adapter.*;
import com.wangxuqin.weatherforecast.entity.City;
import com.wangxuqin.weatherforecast.entity.Region;
import com.wangxuqin.weatherforecast.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/6/10.
 */
public class CityUtilActivity extends Activity {
    private Context context;
    //定义控件
    @Bind(R.id.search_lay)
    LinearLayout search_lay;
    @Bind(R.id.city_et)
    AutoCompleteTextView city_et;
    @Bind(R.id.search_imgBtn)
    ImageButton search_imgBtn;
    @Bind(R.id.cancel_imgBtn)
    ImageButton cancel_imgBtn;
    @Bind(R.id.hide_imgBtn)
    ImageButton hide_imgBtn;
    @Bind(R.id.cityCheck_lv)
    ListView cityCheck_lv;
    //定义数据源
    private ArrayList<City> cityList;
    private ArrayList<Region> regionList;
    private String[] regions;
    private String[] ids;
    //定义适配器
    private RegionAdapter regionAdapter;
    private ArrayAdapter arrayAdapter;
    private StringBuffer tv = new StringBuffer();
    //关键字
    private static final int  RESPONSE_SUCC_CITY=1;
    private static final int  RESPONSE_FIAL_CITY=2;
    DBServer dbServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_check);
        context=this;
        dbServer=new DBServer(context);
        ButterKnife.bind(this);
        regionList=new ArrayList<>();
        regions=new String[]{
                "北京市","天津市","河北省","山西省","内蒙古自治区","辽宁省",
                "吉林省","黑龙江省","上海市","江苏省","浙江省","安徽省",
                "福建省","江西省","山东省","河南省","湖北省","湖南省",
                "广东省","广西壮族自治区","海南省","重庆市","四川省","贵州省",
                "云南省", "西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区",
                "新疆维吾尔自治区","台湾省","香港特别行政区","澳门特别行政区",
                "石家庄市","唐山市","秦皇岛市","邯郸市","邢台市","保定市",
                "张家口市","承德市","沧州市","廊坊市","衡水市",
                "太原市","大同市","阳泉市","长治市","晋城市","朔州市","晋中市",
                "运城市","忻州市","临汾市","吕梁市",
                "呼和浩特市","包头市","乌海市","赤峰市","通辽市","鄂尔多斯市",
                "呼伦贝尔市","巴彦淖尔市","乌兰察布市","兴安盟","锡林郭勒盟",
                "阿拉善盟","沈阳市","大连市","鞍山市","抚顺市","本溪市","丹东市",
                "锦州市","营口市","阜新市","辽阳市","盘锦市","铁岭市","朝阳市",
                "葫芦岛市","长春市","吉林市","四平市","辽源市","通化市","白山市",
                "松原市","白城市","延边朝鲜族自治州","哈尔滨市","齐齐哈尔市","鸡西市",
                "鹤岗市","双鸭山市","大庆市","伊春市","佳木斯市","七台河市","牡丹江市",
                "黑河市","绥化市","大兴安岭地区","南京市","无锡市","徐州市","常州市",
                "苏州市","南通市","连云港市","淮安市","盐城市","扬州市","镇江市","泰州市",
                "宿迁市","杭州市","宁波市","温州市","嘉兴市","湖州市","绍兴市","金华市",
                "衢州市","舟山市","台州市","丽水市","合肥市","芜湖市","蚌埠市","淮南市",
                "马鞍山市","淮北市","铜陵市","安庆市","黄山市","滁州市","阜阳市","宿州市",
                "六安市","亳州市","池州市","宣城市","福州市","厦门市","莆田市","三明市",
                "泉州市","漳州市","南平市","龙岩市","宁德市","南昌市","景德镇市","萍乡市",
                "九江市","新余市","鹰潭市","赣州市","吉安市","宜春市","抚州市","上饶市",
                "济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市",
                "泰安市","威海市","日照市","莱芜市","临沂市","德州市","聊城市","滨州市",
                "菏泽市","郑州市","开封市","洛阳市","平顶山市","安阳市","鹤壁市","新乡市",
                "焦作市","濮阳市","许昌市","漯河市","三门峡市","南阳市","商丘市","信阳市",
                "周口市","驻马店市","济源市","武汉市","黄石市","十堰市","宜昌市","襄阳市",
                "鄂州市","荆门市","孝感市","荆州市","黄冈市","咸宁市","随州市",
                "恩施土家族苗族自治州","仙桃市","潜江市","天门市","神农架林区","长沙市",
                "株洲市","湘潭市","衡阳市","邵阳市","岳阳市","常德市","张家界市","益阳市",
                "郴州市","永州市","怀化市","娄底市","湘西土家族苗族自治州","广州市","韶关市",
                "深圳市","珠海市","汕头市","佛山市","江门市","湛江市","茂名市","肇庆市","惠州市",
                "梅州市","汕尾市","河源市","阳江市","清远市","东莞市","中山市","潮州市","揭阳市",
                "云浮市","南宁市","柳州市","桂林市","梧州市","北海市","防城港市","钦州市","贵港市",
                "玉林市","百色市","贺州市","河池市","来宾市","崇左市","海口市","三亚市","三沙市",
                "五指山市","琼海市","儋州市","文昌市","万宁市","东方市","定安县","屯昌县","澄迈县",
                "临高县","白沙黎族自治县","昌江黎族自治县","乐东黎族自治县","陵水黎族自治县",
                "保亭黎族苗族自治县","琼中黎族苗族自治县","成都市","自贡市","攀枝花市","泸州市",
                "德阳市","绵阳市","广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市",
                "广安市","达州市","雅安市","巴中市","资阳市","阿坝藏族羌族自治州","甘孜藏族自治州",
                "凉山彝族自治州","贵阳市","六盘水市","遵义市","安顺市","毕节市","铜仁市",
                "黔西南布依族苗族自治州","黔东南苗族侗族自治州","黔南布依族苗族自治州","昆明市",
                "曲靖市","玉溪市","保山市","昭通市","丽江市","普洱市","临沧市","楚雄彝族自治州",
                "红河哈尼族彝族自治州","文山壮族苗族自治州","西双版纳傣族自治州","大理白族自治州",
                "德宏傣族景颇族自治州","怒江傈僳族自治州","迪庆藏族自治州","拉萨市","昌都地区",
                "山南地区","日喀则地区","那曲地区","阿里地区","林芝地区","西安市","铜川市","宝鸡市",
                "咸阳市","渭南市","延安市","汉中市","榆林市","安康市","商洛市"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,regions);
        city_et.setAdapter(arrayAdapter);
        ids=new String[]{"11","12","13","14","15","21",
                         "22","23","31","32","33","34",
                         "35","36","37","41","42","43",
                         "44","45","46","50","51","52",
                         "53","54","61","62","63","64",
                         "65","71","81","82"};
        regionList.add(new Region("北京市","11"));
        regionList.add(new Region("天津市","12"));
        regionList.add(new Region("河北省","13"));
        regionAdapter=new RegionAdapter(regionList,context);
        //FileUtil.loadRegionData(this);
        initSearchView(context);
    }
    /**
     * 初始化视图
     */
    public void initSearchView(Context context) {
        //搜索按钮
        search_imgBtn.setOnClickListener(new ButtonClickListener(context));
        //取消按钮,删除AutoCompleteTextView中的数据
        cancel_imgBtn.setOnClickListener(new ButtonClickListener(context));
        //隐藏按钮,隐藏搜索框选项，在ListView中显示省份、直辖市和自治区
        hide_imgBtn.setOnClickListener(new ButtonClickListener(context));
        cityCheck_lv.setOnItemClickListener(new ListViewItemClickListener(context));
        cityCheck_lv.setAdapter(regionAdapter);
    }
        /**
         * 获取城市数据,添加城市
         */

    private void getRegionsList(Context context, String actionId) {
        Log.d("getRegionList",actionId);
        Parameters params = new Parameters();
        params.add("areaID", actionId);
        params.add("action", "getArea");
        params.add("key", "7d5032ca249eb944f7b62348437cecef");
        JuheData.executeWithAPI(context,
                196,
                Api.IP_REGION,
                JuheData.GET, params, rCallBack);
    }

    DataCallBack rCallBack = new DataCallBack() {
        /**
         * 请求成功时调用的方法 statusCode为http状态码,responseString为请求返回数据.
         */
        @Override
        public void onSuccess(int statusCode, String responseString) {
            if(statusCode==30002) {
                Toast.makeText(CityUtilActivity.this,"请检查网络连接",Toast.LENGTH_LONG).show();
            }
            tv.append(responseString);
            try {
                if(statusCode==200) {
                    JSONObject data = new JSONObject(tv.toString());
                    Log.d("Success",tv.toString());
                    JSONObject str=data.getJSONObject("str");
                    JSONArray jsonArray = str.getJSONArray("regions");
                    regionList = new ArrayList<>();
                    Region region =null;
                    /**
                     * 此处解析JSON数据
                     */
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        region = new Region(obj.getString("id"),
                                obj.getString("name"),
                                obj.getString("FullName"),
                                obj.getString("ParentId"),
                                obj.getString("zip"),
                                obj.getString("CreateTime"));
                        regionList.add(region);
                    }
                    //填充数据
                    regionAdapter.setData(regionList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * 请求完成时调用的方法,无论成功或者失败都会调用.
         */
        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            //Toast.makeText(getApplicationContext(), "finish",Toast.LENGTH_SHORT).show();
        }

        /**
         * 请求失败时调用的方法,statusCode为http状态码,throwable为捕获到的异常
         * statusCode:30002 没有检测到当前网络.
         * 30003 没有进行初始化.
         * 0 未明异常,具体查看Throwable信息.
         * 其他异常请参照http状态码.
         */
        @Override
        public void onFailure(int statusCode,
                              String responseString, Throwable throwable) {
            // TODO Auto-generated method stub
            tv.append(throwable.getMessage() + "\n");
            Toast.makeText(CityUtilActivity.this,"请检查网络连接",Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 加载选项菜单
     */
    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.city_item, menu);
        return true;
    }

    //预处理选项菜单
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    //选中菜单项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("AddCity", "onOptionsItemSelected");
        if (R.id.up == item.getItemId()) {
            finish();
        }
        return true;
    }

    //关闭选项菜单
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    /**
     * 内部类，按钮的事件监听器类
     */
    class ButtonClickListener implements View.OnClickListener {
        Context context;
        ButtonClickListener(Context context) {
            this.context=context;
        }
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.search_imgBtn:
                    String text = city_et.getText().toString();
                    Log.d("City",text);
                    if (regions.length == 0) {
                        /**
                         * 此处为数据库查询操作
                         *
                         * regions = new String[]{"北京市", "天津市", "山西省", "河北省", "河南省"};*/
                    }
                    //获取省直辖市自治区在字符串数组中对应的索引，如果找到索引项，
                    //说明输入项是省直辖市自治区
                    int index = -1;
                    String flag="";
                    for (int i = 0; i < ids.length; i++) {
                        if (regions[i].equals(text)) {
                            //获取城市的实际ID值
                            flag=ids[i];
                            index = Integer.parseInt(flag);
                            break;
                        }
                    }
                   /* //如果index<34,表明查询到的是省、直辖市、自治区
                    if (index <= 82 && index != 11 && index != 12 && index != 31 && index != 50 && index != 81 && index != 82) {
                        //获取省、直辖市、自治区,对应的市
                        getRegionsList(context, flag);
                    } else if (index == -1&&!) {//输入了城市名直接将城市名添加到ListView中
                        //加载城市数据，并保存,城市ID 统一用“100"表示
                        Log.d("City",text);
                        regionList.add(new Region(text,"100"));
                        regionAdapter.setData(regionList);
                    } else if (index == -1) {//用户没有输入或输入不合法的字符，申请网络数据，在ListView中显示省份、自治区和直辖市

                    }*/
                    if(text.isEmpty()) {
                        //输入为空，请求全国最高行政区域（直辖市、省份、自治区和特别行政区）列表
                        getRegionsList(context, "0");
                    } else if(index==-1) {
                        //输入不为空，输入的城市名不是全国最高行政区域,统一编号为"100"
                        Log.d("City", text);
                        regionList.clear();
                        regionList.add(new Region(text,"100"));
                        regionAdapter.setData(regionList);
                    } else if(index == 11 || index == 12 || index == 31 || index == 50 || index == 81 || index == 82) {
                        //输入为直辖市和特别行政区
                        regionList.clear();
                        regionList.add(new Region(text,flag));
                        regionAdapter.setData(regionList);
                    } else{
                        //输入为省份,请求对应的城市列表
                        getRegionsList(context, flag);
                    }

                    break;
                case R.id.cancel_imgBtn:
                    city_et.setText("");
                case R.id.hide_imgBtn:
                    //用户没有输入或输入不合法的字符，申请网络数据，在ListView中显示省份、自治区和直辖市
                    getRegionsList(context, "0");
            }
        }
    }

    class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        Context context;
        ListViewItemClickListener(Context context) {
            this.context=context;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //加载城市数据，并保存
            Region region = (Region) regionAdapter.getItem(position);
            int flag = Integer.parseInt(region.getId());
            if (flag < 82 && flag != 11 && flag != 12 && flag != 31 && flag != 50 && flag != 81 && flag != 82) {
                //如果点击的是省份请求对应的城市
                getRegionsList(context, region.getId());
            } else {
                //如果请求的是城市
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                ArrayList<City> cityList = (ArrayList<City>) bundle.getSerializable("cityList");
                ArrayList<City> cityArrayList=dbServer.findCities();

                if(!isExist(cityList,region)) {
                    //运用散列函数生成散列值
                    City city=new City();
                    city.setCityName(region.getName());
                    Log.d("CityUtilActivity", "验证城市选择前，数据库中cityList的内容" + cityArrayList.toString());
                    Log.d("CityUtilActivity", "验证城市选择前，对象中cityList的内容" + cityList.toString());
                    try{
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        byte[] result = md.digest(city.getCityName().getBytes());
                        BigInteger b = new BigInteger(result);
                        city.setId(b.toString());
                        Log.d("CityUtilActivity", "验证城市是否为空" + city.toString());
                        /**
                         * 写入数据库
                         */
                        if(!dbServer.isCityExists(city.getId())&&!cityList.contains(city)) {
                            dbServer.addCity(city);
                            cityList.add(city);
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                     cityArrayList=dbServer.findCities();
                    Log.d("CityUtilActivity", "验证城市选择完毕以后，数据库中cityList的大小" + String.valueOf(cityArrayList.size()));
                    Log.d("CityUtilActivity", "验证城市选择完毕以后，对象中cityList的大小" + String.valueOf(cityList.size()));
                    Log.d("CityUtilActivity","验证城市选择完毕以后，数据库中cityList的内容："+cityArrayList.toString());
                    Log.d("CityUtilActivity","验证城市选择完毕以后，对象中cityList的内容："+cityList.toString());

                }

                bundle.putSerializable("cityList", cityList);
                intent.putExtras(bundle);
                CityUtilActivity.this.setResult(RESPONSE_SUCC_CITY, intent);
                CityUtilActivity.this.finish();
            }
        }
        /**
         * 判断城市名有无重复
         */
        public boolean isExist(ArrayList<City> cityList,Region region) {
            boolean result=false;
            for(City city:cityList) {
                if(region.getName().equals(city.getCityName())) {
                    result=true;
                    break;
                }
            }
            return result;
        }
    }
}

