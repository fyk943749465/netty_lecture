package com.shengsiyuan.dp.principle.singleresponsibility;

public class SingleResponsibility1 {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run(("汽车"));
        vehicle.run("飞机");
    }
}

// 方式１
// 1. 在方式１的run方法中，违反了单一指责原则
// 2. 因为所有的交通工具，不管摩托车，汽车，废弃，都是在跑
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " is runing on road....");
    }
}
