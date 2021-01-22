package com.shengsiyuan.dp.decorator;

public class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        setDes("milk");
        setPrice(2.0f);
    }
}
