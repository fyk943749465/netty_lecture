package com.shengsiyuan.dp.factory.abstractfactory.pizza;

public abstract class Pizza {

    private String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + " 烘烤完成");
    }

    public void cut() {
        System.out.println(name + " 切割完成");
    }

    public void box() {
        System.out.println(name + " 装箱完成");
    }

    public void setName(String name) {
        this.name = name;
    }

}
