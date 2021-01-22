package com.shengsiyuan.dp.observer;

public class Client {

    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();

        CurrentConditions currentConditions = new CurrentConditions();
        BaiduConditions baiduConditions = new BaiduConditions();

        weatherData.registerOBserver(currentConditions);
        weatherData.registerOBserver(baiduConditions);

        System.out.println("通知各个注册的观察");

        weatherData.setData(10, 100, 30.3f);

    }
}
