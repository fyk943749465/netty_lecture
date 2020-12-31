package com.shengsiyuan.dp.prototype.v1;

public class Client {

    public static void main(String[] args) {

        Sheep sheep = new Sheep("tom", "white", 1);

        Sheep sheep2 = new Sheep(sheep.getName(), sheep.getColor(), sheep.getAge());

        System.out.println(sheep);
        System.out.println(sheep2);
    }
}
