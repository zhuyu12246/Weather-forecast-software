package com.example.weather;

import androidx.annotation.NonNull;

public class ForeCastClass {

    /**
     * date : 06
     * high : 高温 24℃
     * low : 低温 18℃
     * ymd : 2024-05-06
     * week : 星期一
     * sunrise : 05:06
     * sunset : 18:33
     * aqi : 48
     * fx : 北风
     * fl : 2级
     * type : 多云
     * notice : 阴晴之间，谨防紫外线侵扰
     */

    private String date;
    private String high;
    private String low;
    private String ymd;
    private String week;
    private String sunrise;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high.replace("高温","");
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low.replace("低温","");
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("日期 %s, 天气: %s, 风力: %s, 风向: %s, 温度: %s",date,type,fl,fx,getLow() + "~" + getHigh());
    }
}
