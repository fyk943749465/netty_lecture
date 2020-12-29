package com.shengsiyuan.volatilestudy;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

//    public volatile int inc = 0;

//    1
//    public synchronized void increase() {
//        inc ++;
//    }

//    2
//    Lock lock = new ReentrantLock();
//
//    public void increase() {
//        lock.lock();
//        try {
//            inc ++;
//        } finally {
//            lock.unlock();
//        }
//    }

//    3
    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }


    public static void main(String[] args) {

        final Test test = new Test();
        for (int i = 0; i < 10; ++i) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; ++j) {
                        test.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(test.inc);
    }
}
