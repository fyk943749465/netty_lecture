package com.shengsiyuan.dp.factory.abstractfactory.order;


import com.shengsiyuan.dp.factory.abstractfactory.pizza.Pizza;

/**
 * 一个抽象工厂模式的抽象层（接口）
 */
public interface AbsFactory {

    public Pizza createPizza(String orderType);
}
