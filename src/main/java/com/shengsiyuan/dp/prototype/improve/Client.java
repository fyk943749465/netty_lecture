package com.shengsiyuan.dp.prototype.improve;

public class Client {

    public static void main(String[] args) {

        Sheep sheep = new Sheep("tom", "white", 1);


        Sheep sheep1 = (Sheep) sheep.clone();

        System.out.println("sheep " + sheep + sheep.hashCode());
        System.out.println("sheep1" + sheep1 + sheep1.hashCode());
    }
}
