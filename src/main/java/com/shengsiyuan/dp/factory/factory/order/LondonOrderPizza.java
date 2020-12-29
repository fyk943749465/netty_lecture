package com.shengsiyuan.dp.factory.factory.order;

import com.shengsiyuan.dp.factory.factory.pizza.LondonCheessPizza;
import com.shengsiyuan.dp.factory.factory.pizza.LondonPepperPizza;
import com.shengsiyuan.dp.factory.factory.pizza.Pizza;

public class LondonOrderPizza extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LondonCheessPizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new LondonPepperPizza();
        }
        return pizza;
    }
}
