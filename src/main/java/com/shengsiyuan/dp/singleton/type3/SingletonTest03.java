package com.shengsiyuan.dp.singleton.type3;

public class SingletonTest03 {

    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance == instance1);
        System.out.println("hashcode:" + instance.hashCode() + " " + instance1.hashCode());
    }
}

class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    // 提供一个静态的公有方法，当使用到该方法时，才去创建instance
    // 即懒汉式写法
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
