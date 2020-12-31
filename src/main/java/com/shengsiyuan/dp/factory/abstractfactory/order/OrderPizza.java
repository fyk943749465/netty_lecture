package com.shengsiyuan.dp.factory.abstractfactory.order;

import com.shengsiyuan.dp.factory.abstractfactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    private AbsFactory factory;

    public OrderPizza(AbsFactory factory) {
        setFactory(factory);
    }

    private void setFactory(AbsFactory factory) {
        Pizza pizza = null;
        String orderType = ""; //用户输入
        this.factory = factory;

        do {
            orderType = getType();
            pizza = factory.createPizza(orderType);
            if (pizza == null) {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    private String getType() {

        BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input pizza type:");
        String str = null;
        try {
            str = strin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }
}
