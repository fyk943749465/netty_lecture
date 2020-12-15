package com.shengsiyuan.decorator;

/**
 * 装饰角色，要实现抽象构建角色
 * 装饰角色要持有一个抽象构建角色的引用
 */
public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
