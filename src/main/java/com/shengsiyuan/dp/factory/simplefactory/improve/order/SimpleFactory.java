package com.shengsiyuan.dp.factory.simplefactory.improve.order;

import com.shengsiyuan.dp.factory.simplefactory.improve.pizza.CheessPizza;
import com.shengsiyuan.dp.factory.simplefactory.improve.pizza.GreekPizza;
import com.shengsiyuan.dp.factory.simplefactory.improve.pizza.Pizza;


public class SimpleFactory {

    public Pizza createPizza(String orderType) {

        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊批萨");
        } else if ("cheess".equals(orderType)) {
            pizza = new CheessPizza();
            pizza.setName("奶酪批萨");
        }
        return pizza;
    }
}
