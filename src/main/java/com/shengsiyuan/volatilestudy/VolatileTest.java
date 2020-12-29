package com.shengsiyuan.volatilestudy;

import java.util.concurrent.TimeUnit;

/**
 * 演示线程可见性的代码
 */

public class VolatileTest {


    volatile boolean running = true;

    void m() {
        System.out.println("m start");
        while(running) {

        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {

        VolatileTest t = new VolatileTest();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;

    }
}
