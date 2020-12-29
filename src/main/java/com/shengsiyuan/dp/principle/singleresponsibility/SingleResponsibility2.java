package com.shengsiyuan.dp.principle.singleresponsibility;

public class SingleResponsibility2 {

    public static void main(String[] args) {

        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}

// 方案２
// 1. 遵守了单一指责原则
// 2. 但是这样做的改动很大，即将类的分解，同时修改客户端
// 3. 改进，直接修改Vehicle, 这样改动的代码会比较少

class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " is running on road");
    }
}

class AirVehicle {

    public void run(String vehicle) {
        System.out.println(vehicle + " is flying in sky");
    }
}

class WaterVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " is swimming in water");
    }
}
