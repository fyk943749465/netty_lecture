package com.shengsiyuan.dp.factory.factory.order;

import com.shengsiyuan.dp.factory.factory.pizza.BeiJingCheessPizza;
import com.shengsiyuan.dp.factory.factory.pizza.BeiJingPepperPizza;
import com.shengsiyuan.dp.factory.factory.pizza.Pizza;

public class BeiJingOrderPizza extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BeiJingCheessPizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new BeiJingPepperPizza();
        }
        return pizza;
    }
}
