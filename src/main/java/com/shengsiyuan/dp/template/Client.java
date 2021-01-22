package com.shengsiyuan.dp.template;

public class Client {

    public static void main(String[] args) {

        // 制作红豆豆浆
        System.out.println("----制作红豆豆浆----");
        SoyaMilk readBeanSoayMilk = new RedBeanSoayMilk();
        readBeanSoayMilk.make();

        System.out.println("----制作花生豆浆----");
        SoyaMilk peanutSoayMilk = new PeanutSoayMilk();
        peanutSoayMilk.make();
    }
}
