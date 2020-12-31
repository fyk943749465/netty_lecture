package com.shengsiyuan.dp.factory.abstractfactory.order;

import com.shengsiyuan.dp.factory.abstractfactory.pizza.*;

public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("使用的抽象工厂模式");
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LondonCheessPizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new LondonPepperPizza();
        }
        return pizza;
    }
}
