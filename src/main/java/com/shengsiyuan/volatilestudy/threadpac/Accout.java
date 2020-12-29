package com.shengsiyuan.volatilestudy.threadpac;

import java.util.concurrent.TimeUnit;

public class Accout {

    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {

        Accout a = new Accout();

        new Thread(() -> a.set("zhangsan", 100.0)).start();

        System.out.println(a.getBalance());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());
    }
}
