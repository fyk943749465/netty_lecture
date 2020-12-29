package com.shengsiyuan.dp.factory.factory.order;



import com.shengsiyuan.dp.factory.factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;

        do {
            String orderType = getType();
            pizza = createPizza(orderType); // 抽象的方法，由工厂子类完成
            if (pizza == null){
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    // 定义一个抽象方法，createPizza, 让各个工厂子类自己实现
    public abstract Pizza createPizza(String orderType);

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
