package com.shengsiyuan.dp.factory.simplefactory.improve.pizza;

public class CheessPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("奶酪批萨准备原材料");
    }
}
