package com.shengsiyuan.decorator;

public class ConcreteDecorator2 extends Decorator{

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAntherThing();
    }

    private void doAntherThing() {
        System.out.println("功能C");
    }
}
