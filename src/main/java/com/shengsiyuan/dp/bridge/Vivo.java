package com.shengsiyuan.dp.bridge;

public class Vivo implements Brand {
    @Override
    public void open() {
        System.out.println("Vivo 手机开机了");
    }

    @Override
    public void close() {
        System.out.println("Vivo 手机关机了");
    }

    @Override
    public void call() {
        System.out.println("Vivo 手机打电话");
    }
}
