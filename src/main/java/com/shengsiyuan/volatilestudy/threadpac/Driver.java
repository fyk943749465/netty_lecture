package com.shengsiyuan.volatilestudy.threadpac;

import java.util.concurrent.CountDownLatch;

public class Driver {

    public static final int N = 10;
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        doSomethingElse();
        startSignal.countDown(); //　因为初始化为１，这里调用countDown会变为０，让其他线程都开始执行
        doSomethingElse();
        doneSignal.await();
    }

    private static void doSomethingElse() {
        System.out.println("sleeping...");
    }

    static class Worker implements  Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();  //这里进入阻塞状态
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        void doWork() {
            System.out.println("I'm working...");
        }
    }
}

