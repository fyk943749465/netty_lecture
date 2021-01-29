package com.shengsiyuan.nio.book.crazymakercircle.coccurent;

import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class JavaFutureDemo {

    public static final int SLEEP_GAP = 500;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean>
    {

        @Override
        public Boolean call() throws Exception {
            try {
                Logger.info("洗好水壶");
                Logger.info("灌上凉水");
                Logger.info("放在火上");

                // 线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                Logger.info("水开了");
            } catch (InterruptedException e) {
                Logger.info("发生异常被中断");
                return false;
            }
            Logger.info("运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean>
    {

        @Override
        public Boolean call() throws Exception {
            try {
                Logger.info("洗茶壶");
                Logger.info("洗茶杯");
                Logger.info("拿茶叶");

                // 线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                Logger.info("洗完了");
            } catch (InterruptedException e) {
                Logger.info("清洗工作，发生异常被中断");
                return false;
            }
            Logger.info("清洗工作，运行结束");
            return true;
        }
    }

    public static void drinkTea(boolean waterOk, boolean cupOk) {
        if (waterOk && cupOk) {
            Logger.info("愉快地　泡茶喝");
            return;
        }

        if (!waterOk) {
            Logger.info("烧水失败，没有茶喝水");
        }

        if (!cupOk) {
            Logger.info("杯子兮不来，没有茶喝");
        }
    }

    public static void main(String[] args) {

        Callable<Boolean> hJob = new HotWaterJob();
        FutureTask<Boolean> hTask =
                new FutureTask<>(hJob);
        Thread hThread = new Thread(hTask, "** 烧水-Thread");

        Callable<Boolean> wJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(wJob);
        Thread wThread = new Thread(wTask, "$$ 清洗-Thread");
        hThread.start();
        wThread.start();
        Thread.currentThread().setName("主线程");

        try {

            boolean waterOk = hTask.get();
            boolean cupOk = wTask.get();

            drinkTea(waterOk, cupOk);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            Logger.info(getCurThreadName() + "发生异常被中断");
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
        Logger.info(getCurThreadName() + " 运行结束");
    }
}
