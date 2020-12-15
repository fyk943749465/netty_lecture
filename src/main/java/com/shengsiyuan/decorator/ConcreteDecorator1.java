package com.shengsiyuan.decorator;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnontherThing();
    }

    private void doAnontherThing() {
        System.out.println("功能B");
    }
}
