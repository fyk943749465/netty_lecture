package com.shengsiyuan.volatilestudy.threadpac;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量的同步容器，拥有put和get方法，以及getCount方法
 * 能够支持２个生产者线程以及１０个消费者线程的阻塞调用
 * @param <T>
 */
public class MyContainer1<T> {

    private final LinkedList<T> lists = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {

        while (lists.size() == MAX) {
            try {
                this.wait();  // 当前线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++ count;
        this.notifyAll(); //唤醒所有阻塞的线程
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t = lists.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {

        MyContainer1<String> c = new MyContainer1<>();

        for (int i = 0; i < 10; ++i) {
            new Thread(() -> {
                for (int j = 0; j < 5; ++j) {
                    System.out.println(Thread.currentThread().getName() + " " + c.get());
                }
            }, "consumer" + i).start();
        }

        for (int i = 0; i < 2; ++i) {
            new Thread(() -> {
                for (int j = 0; j < 25; ++j) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "Producer" + i).start();
        }
    }
}
