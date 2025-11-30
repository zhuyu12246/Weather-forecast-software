package com.example.weather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
//    天气图片
    public ImageView imageView;
//    日期now
    public TextView textView;
//    最高温度
    public TextView textView3;
//    最低温度
    public TextView textView15;
//    天气
    public  TextView textView14;
    private List<Item> dataList;

    String weatherMsg;

    public CustomAdapter(List<Item> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        设置数据
        Item item = dataList.get(position);

        imageView  = holder.itemView.findViewById(R.id.imageView);
        imageView.setImageResource(item.getWeatherImage());

        textView = holder.itemView.findViewById(R.id.textView);
        textView.setText(item.getNow());

        textView3 = holder.itemView.findViewById(R.id.textView3);
        textView3.setText(item.getTemperatureH());

        textView15 = holder.itemView.findViewById(R.id.textView15);
        textView15.setText(item.getTemperatureL());

        textView14 = holder.itemView.findViewById(R.id.textView14);
        textView14.setText(item.getWeather());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Position = holder.getAdapterPosition();
                Item item = dataList.get(Position);

                switch (item.getWeather()){
                    case "阴":
                        weatherMsg = "今天天气为阴,不会出太阳";
                        break;
                    case "多云":
                        weatherMsg = "今天天气多云，温度十分舒适";
                        break;
                    case "晴":
                        weatherMsg = "今天天气为晴天,适合外出游玩";
                        break;
                    case "中雨":
                        weatherMsg = "今天天气为下中雨,记得加衣服和带伞雨伞";
                        break;
                    case "小雨":
                        weatherMsg = "今天天气为小雨,记得带伞";
                        break;
                    case "大雨":
                        weatherMsg = "今天天气为大雨,记得加衣服和带伞雨伞";
                        break;
                }

                // 创建AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                // 设置对话框标题和消息
                builder.setTitle("详细展示");
//                builder.setMessage("你点击了第" + Position + "个条目");
                builder.setMessage("温度:" + item.getTemperatureL() + "~" + item.getTemperatureH() + '\n' + "天气:" + item.getWeather() + '\n' + "每日寄语:" + weatherMsg);
                // 设置对话框按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        /*
                        * 什么都不用做
                        * */
                    }
                });
                // 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}

