package com.shengsiyuan.volatilestudy.threadpac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题
 * 实现一个容器，提供两个方法，　add, size
 * 写两个线程，线程１添加１０个元素到容器中，线程２实现监控元素的个数，当个数到５个时，线程２给出提示并结束
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就色首先t2监听才可以
 */
public class NotifyFreeLock {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        NotifyFreeLock notifyFreeLock = new NotifyFreeLock();

        final Object lock = new Object();

        new Thread(() -> {

            System.out.println("start t2 ...");
            synchronized (lock) {
                if (notifyFreeLock.size() != 5) {
                    try {
                        lock.wait(); // 接受到notify信号后，被唤醒，继续执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify(); //线程快要结束的时候，唤醒另一个线程执行，等该线程结束，自动让出锁
            }
            System.out.println("end t2!");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("start t1 ...");
            synchronized (lock) {
                for (int i = 0; i < 10; ++i) {

                    notifyFreeLock.add(new Object());
                    System.out.println("add " + i);
                    if (notifyFreeLock.size() == 5) {
                        lock.notify();  //唤醒另一个线程
                        try {
                            lock.wait();// 让出锁，让另一个线程执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1").start();
    }
}
