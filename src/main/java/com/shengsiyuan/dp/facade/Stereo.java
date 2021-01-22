package com.shengsiyuan.dp.facade;

public class Stereo {

    // 使用单例模式，使用饿汉式

    private static Stereo instance = new Stereo();

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("Stereo on");
    }

    public void off() {
        System.out.println("Stereo off");
    }

    public void trunUp() {
        System.out.println("Stereo turns up");
    }

    // ...
}
