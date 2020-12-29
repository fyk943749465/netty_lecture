package com.shengsiyuan.volatilestudy.threadpac;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread{

    private static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 100; ++i) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "gets lock");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        ReentrantLock5 rl = new ReentrantLock5();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);

        th1.start();
        th2.start();
    }
}
