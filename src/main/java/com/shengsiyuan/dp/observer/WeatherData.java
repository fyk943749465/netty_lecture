package com.shengsiyuan.dp.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private float temperature;
    private float pressure;
    private float humidity;

    private List<Observer> observerList;

    public WeatherData() {
        this.observerList = new ArrayList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    @Override
    public void registerOBserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(observerList.contains(o)) {
            observerList.remove(o);
        }
    }

    public void setData(float temperature, float pressure, float humidity) {
        setTemperature(temperature);
        setPressure(pressure);
        setHumidity(humidity);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(e -> e.update(this.temperature, this.pressure, this.humidity));
    }
}
