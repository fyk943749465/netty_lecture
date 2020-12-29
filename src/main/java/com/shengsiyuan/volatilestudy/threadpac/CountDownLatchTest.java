package com.shengsiyuan.volatilestudy.threadpac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    private static CountDownLatch lock1 = new CountDownLatch(1);
    private static CountDownLatch lock2 = new CountDownLatch(1);

    volatile List lists = new ArrayList<>();

    void add(Object o) {
        lists.add(o);
    }

    int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        CountDownLatchTest c = new CountDownLatchTest();

        Thread t1 = new Thread(() -> {

            System.out.println("start t1");
            for (int i = 0; i < 10; ++i) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    lock1.countDown();
                    try {
                        lock2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("end t1");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("start t2");
            try {
                lock1.await();
                System.out.println("满足条件退出");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end t2");
            lock2.countDown();
        });

        t2.start();
        t1.start();

    }
}
