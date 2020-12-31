package com.shengsiyuan.dp.factory.abstractfactory.order;

import com.shengsiyuan.dp.factory.abstractfactory.pizza.BeiJingCheessPizza;
import com.shengsiyuan.dp.factory.abstractfactory.pizza.BeiJingPepperPizza;
import com.shengsiyuan.dp.factory.abstractfactory.pizza.Pizza;

public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("使用的抽象工厂模式");
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BeiJingCheessPizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new BeiJingPepperPizza();
        }
        return pizza;
    }
}
