package com.shengsiyuan.volatilestudy.threadpac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TestLocakSupportWithoutSleep {

    volatile List lists = new ArrayList<>();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        TestLocakSupportWithoutSleep c = new TestLocakSupportWithoutSleep();

        t1 = new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; ++i) {
                c.add(new Object());
                System.out.println("add " + i);

                if (c.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("t1 end");
        }, "t1");

        t2 = new Thread(() -> {

            System.out.println("t2 start");
            LockSupport.park();

            System.out.println("t2 end");
            LockSupport.unpark(t1);
        }, "t2");

        t2.start();  // 保证t2先执行
        t1.start();


    }
}
