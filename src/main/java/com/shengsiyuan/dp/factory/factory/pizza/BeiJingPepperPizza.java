package com.shengsiyuan.dp.factory.factory.pizza;

public class BeiJingPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京的胡椒pizza");
        System.out.println("北京的胡椒pizza 准备原材料");
    }
}
