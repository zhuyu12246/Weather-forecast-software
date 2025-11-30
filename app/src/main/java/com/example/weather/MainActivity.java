package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Item> dataList = new ArrayList<>();
//    现在时间
    TextView textView2;
//    当前气温
    TextView textView4;
//    空气质量
    TextView textView8;
//    湿度
    TextView textView9;
//    天气
    TextView textView10;
//    城市名
    TextView textView11;
    RecyclerView recyclerView;
//搜索
    EditText editTextText;
//    历史列表
    List<String> list;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextText = findViewById(R.id.editTextText);
        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);

        // 初始化RecyclerView
        recyclerView = findViewById(R.id.re);

        // 设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getWeather("宁波");
//        EventBus.getDefault().register(this);

        // 实例化适配器并设置给RecyclerView
        CustomAdapter adapter = new CustomAdapter(dataList);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        /*
        * Spinner 设置下拉列表展示历史记录-------------
        * */
//        本地存储
//        SharedPreferences sharedPreferences = getSharedPreferences("myshare",MainActivity.MODE_PRIVATE);


        Spinner spinner = findViewById(R.id.spinner2);
        list = new ArrayList<>();
        list.add("==历史记录==");
        // 获取SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("myshare",MainActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();//获取编辑器

// 获取所有键值对数据
        Map<String, ?> allEntries = sharedPreferences.getAll();

// 遍历所有数据
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
//            Object value = entry.getValue();
            list.add(key);
        }
//        list.add("成都");
//        list.add("杭州");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
// 设置下拉列表的样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// 将适配器设置给Spinner
        spinner.setAdapter(adapter1);
//        处理用户的选项
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            选择
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    // 获取用户选择的选项数据
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    // 处理用户选择的选项
                    editTextText.setText(selectedItem);
                }

            }
//            不选择
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 当没有选项被选中时执行
                /*
                * 什么都不做
                * */
            }
        });
//--------------------------------------------------------

    }

    public void onButtonClick(View view) {
        String textInput = editTextText.getText().toString();
        if(!list.contains(textInput)){
            list.add(textInput);
            editor.putString(textInput,textInput);
            editor.commit();//提交修改
        }
        getWeather(textInput);
    }
    public void onAboutClick(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }

    private void getWeather(String cityName){
        String cityCode = getCityCode(cityName);
        String url = "http://t.weather.sojson.com/api/weather/city/" + cityCode;
        MyHttpConnection httpConnection = new MyHttpConnection();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String weather = httpConnection.doGet(url);
                JSONObject weatherJson = JSON.parseObject(weather);
//                解决无城市时候报错
                if(weatherJson != null && !weatherJson.isEmpty()){
                    JSONObject dataJson = weatherJson.getJSONObject("data");
                    JSONObject cityInfo = weatherJson.getJSONObject("cityInfo");
                    if(dataJson != null && !dataJson.isEmpty()){
                        JSONArray foreCastArray = dataJson.getJSONArray("forecast");

                        String time = weatherJson.getString("time").substring(0, 10);
                        String wendu = dataJson.getString("wendu");
                        String kongqi = dataJson.getString("quality");
                        String shidu = dataJson.getString("shidu");
                        String type = foreCastArray.getJSONObject(0).getString("type");
                        String cityName =cityInfo.getString("city");


                        // 更新UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(time);
                                textView4.setText(wendu + "°C");
                                textView8.setText(kongqi);
                                textView9.setText(shidu);
                                textView10.setText(type);
                                textView11.setText(cityName);
                            }
                        });

                        // Prepare forecast data and update the list
                        List<Item> newDataList = new ArrayList<>();
                        for(int i = 0; i < foreCastArray.size(); i++){
                            JSONObject item = foreCastArray.getJSONObject(i);
                            ForeCastClass foreCastClass = item.toJavaObject(ForeCastClass.class);
                            if(i != 0){
                                String w = foreCastClass.getType();
                                String th = foreCastClass.getHigh();
                                String tl = foreCastClass.getLow();
                                String now = foreCastClass.getYmd();
                                int wImage = R.mipmap.ic_launcher;
                                switch (w){
                                    case "阴":
                                        wImage = R.mipmap.yintian;
                                        break;
                                    case "多云":
                                        wImage = R.mipmap.duoyun;
                                        break;
                                    case "晴":
                                        wImage = R.mipmap.qin;
                                        break;
                                    case "中雨":
                                        wImage = R.mipmap.zhongyu;
                                        break;
                                    case "小雨":
                                        wImage = R.mipmap.xiaoyu;
                                        break;
                                    case "大雨":
                                        wImage = R.mipmap.dayu;
                                        break;
                                }
                                /*
                                 * weather
                                 * temperatureH
                                 * temperatureL
                                 * now
                                 * weatherImage
                                 * */
                                newDataList.add(new Item(w, th, tl, now, wImage));
                            }
                        }

                        // 更新list
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataList.clear();
                                dataList.addAll(newDataList);
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }
                        });
                    }
                }


            }
        }).start();
    }


    private String getCityCode(String cityName){
        String result = null;
        try {
            InputStream stream = getAssets().open("city.json");
            StringBuffer buffer = new StringBuffer();
            int len;
            byte []data = new byte[1024];

            while((len = stream.read(data)) != -1){
                buffer.append(new String(data,0,len));
            }
            stream.close();

            JSONObject cityJson = JSONObject.parseObject(buffer.toString());
            result = cityJson.getString(cityName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void handleMessage(MyMessageEvent msg){
//        TextView tv = findViewById(R.id.textView);
//        tv.setText(msg.getMessage());
//    }
}