package com.shengsiyuan.dp.factory.simplefactory.improve.order;

import com.shengsiyuan.dp.factory.simplefactory.improve.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    // 定义一个简单工厂对象
    private SimpleFactory simpleFactory;

    public OrderPizza(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);
    }

    public void setFactory(SimpleFactory simpleFactory) {

        this.simpleFactory = simpleFactory;
        Pizza pizza = null;
        do {
            String orderType = getType(); // 用户输入的
            pizza = this.simpleFactory.createPizza(orderType);
            if (pizza == null) {
                System.out.println("订购批萨失败");
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
