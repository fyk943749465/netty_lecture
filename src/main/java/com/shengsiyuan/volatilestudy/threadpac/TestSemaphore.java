package com.shengsiyuan.volatilestudy.threadpac;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main(String[] args) {

        Semaphore s = new Semaphore(1);

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T1 running ....");
                Thread.sleep(200);
                System.out.println("T1 running ....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 running ....");
                Thread.sleep(200);
                System.out.println("T2 running ....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }
}
