package com.shengsiyuan.dp.visitor;

public class Man extends Person{
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
