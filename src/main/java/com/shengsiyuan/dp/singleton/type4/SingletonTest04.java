package com.shengsiyuan.dp.singleton.type4;

public class SingletonTest04 {

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
    // 加入synchronized关键字，解决多线程并访问时，创建多个实例的问题
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
