package com.shengsiyuan.dp.observer;

public interface Subject {

    void registerOBserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}
