package com.shengsiyuan.dp.decorator;

// 这里是调味品
public class Chocolate extends Decorator {

    public Chocolate(Drink obj) {
        super(obj);
        setDes("巧克力");
        setPrice(3.5f); // 调味品的价格
    }
}
