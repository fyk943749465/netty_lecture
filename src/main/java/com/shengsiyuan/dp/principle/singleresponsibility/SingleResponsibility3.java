package com.shengsiyuan.dp.principle.singleresponsibility;

public class SingleResponsibility3 {

    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runWater("轮船");
        vehicle2.runAir("飞机");
    }
}

// 方式３的分析
// 1. 这种修改方式对原来的类没有做大的修改，只是增加方法
// 2. 在一定程度上没有遵守单一指责原则（在类上遵守了单一职责原则，但是在方法级别上没有遵守单一职责原则）
class Vehicle2 {

    public void run(String vehicle) {
        System.out.println(vehicle + " is running on road ...");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + " is flying in sky");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + " is swimming in water");
    }
}