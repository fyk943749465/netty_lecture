package com.shengsiyuan.dp.factory.simplefactory.order;

import com.shengsiyuan.dp.factory.simplefactory.pizza.CheessPizza;
import com.shengsiyuan.dp.factory.simplefactory.pizza.GreekPizza;
import com.shengsiyuan.dp.factory.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;

        do {
            String PizzaType = getType();
            if ("greek".equals(PizzaType)) {
                pizza = new GreekPizza();
                pizza.setName("希腊批萨");
            } else if ("cheess".equals(PizzaType)) {
                pizza = new CheessPizza();
                pizza.setName("奶酪批萨");
            } else {
                break;
            }
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
