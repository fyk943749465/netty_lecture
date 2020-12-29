package com.shengsiyuan.dp.singleton.type6;

public class SingletonTest06 {

    public static void main(String[] args) {

    }
}

// 静态内部类完成
class Singleton {

    // 构造器私有化
    private Singleton() {}

    // 写一个静态内部类，该类中一个静态属性Singleton
    private static class SingletonInstance {
        private static final Singleton INSANTCE = new Singleton();
    }

    // 提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE
    public static Singleton getInstance() {
        return SingletonInstance.INSANTCE;
    }

}
