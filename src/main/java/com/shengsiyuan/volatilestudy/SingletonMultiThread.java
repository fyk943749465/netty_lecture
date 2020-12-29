package com.shengsiyuan.volatilestudy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * double check 和　voletile
 */
public class SingletonMultiThread {

    private static volatile SingletonMultiThread INSTANCE; // JIT

    private SingletonMultiThread() {

    }

    ConcurrentHashMap chm  = null;

    public static SingletonMultiThread getInstance() {

        if (INSTANCE == null) {
            synchronized (SingletonMultiThread.class) {
                if (INSTANCE == null) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingletonMultiThread();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("M");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                System.out.println(SingletonMultiThread.getInstance().hashCode());
            }).start();
        }
    }
}
