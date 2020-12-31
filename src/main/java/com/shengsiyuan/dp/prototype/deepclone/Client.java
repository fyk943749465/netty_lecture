package com.shengsiyuan.dp.prototype.deepclone;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {

        DeepPrototype p = new DeepPrototype();
        p.name = "宋江";
        p.deepCloneableTarget = new DeepCloneableTarget("大牛", "大牛类");

        // 方式１　进行深拷贝

        DeepPrototype p2 = (DeepPrototype)p.clone();

        // 方式２　深度克隆
        DeepPrototype p3 = (DeepPrototype)p.deepClone();

        System.out.println(p.deepCloneableTarget.hashCode());
        System.out.println(p2.deepCloneableTarget.hashCode());
        System.out.println(p3.deepCloneableTarget.hashCode());
    }
}
