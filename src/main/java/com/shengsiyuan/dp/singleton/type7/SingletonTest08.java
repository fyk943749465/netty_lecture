package com.shengsiyuan.dp.singleton.type7;

public class SingletonTest08 {

    public static void main(String[] args) {


        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;

        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
    }
}

enum Singleton {
    INSTANCE; //属性
    public void sayOk() {
        System.out.println("ok~");
    }
}
