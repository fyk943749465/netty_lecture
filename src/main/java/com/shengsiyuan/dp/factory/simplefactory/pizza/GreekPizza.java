package com.shengsiyuan.dp.factory.simplefactory.pizza;

public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("希腊批萨准备原材料");
    }
}
