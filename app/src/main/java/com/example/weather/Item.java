package com.example.weather;

public class Item {
    private String weather;
    private String temperatureH;
    private String temperatureL;
    private String now;
    private int weatherImage;


    public Item(String weather, String temperatureH,
                String temperatureL,String now,int weatherImage) {
        this.weather = weather;
        this.temperatureH = temperatureH;
        this.temperatureL = temperatureL;
        this.now = now;
        this.weatherImage = weatherImage;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemperatureH() {
        return temperatureH;
    }

    public String getTemperatureL() {
        return temperatureL;
    }
    public String getNow() {
        return now;
    }
    public int getWeatherImage() {
        return weatherImage;
    }
}
