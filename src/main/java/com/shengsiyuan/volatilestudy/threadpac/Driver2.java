package com.shengsiyuan.volatilestudy.threadpac;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Driver2 {

    private static final int N = 10;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(N);

//        Executor e = Runnable::run;

//        Executor e = runnable -> runnable.run();

//        Executor e = runnable -> {
//            runnable.run();
//        };

        Executor e = command -> new Thread(command).start();

        for (int i = 0; i < N; ++i) {
            e.execute(new WorkerRunnable(doneSignal, i));
        }

        doneSignal.await();
        System.out.println("main thread is over!");
    }

}


class WorkerRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;

    WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {

        doWork(i);
        doneSignal.countDown();
    }

    void doWork(int i) {
        System.out.println( i + " is working ....");
    }
}


