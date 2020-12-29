package com.shengsiyuan.dp.singleton.type5;

public class SingletonTest05 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance == instance1);
        System.out.println("hashcode:" + instance.hashCode() + " " + instance1.hashCode());
    }
}

// 懒汉式（线程安全，同步方法）
class Singleton {
    private static volatile Singleton singleton;
    private Singleton() {}

    // 提供一个静态的公有方法，加入双重检查代码，解决线程安全问问，同时解决懒加载问题
    // 同时保证了效率
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}