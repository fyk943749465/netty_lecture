package com.shengsiyuan.dp.singleton.type2;

public class SingletonTest02 {

    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance == instance1);
        System.out.println("hashcode:" + instance.hashCode() + " " + instance1.hashCode());
    }
}

// 饿汉式
class Singleton {

    //1. 构造器私有化,外部不能new
    private Singleton() {

    }

    //2. 本类内部创建对象势力
    private final static Singleton instance;

    // 在静态代码块中创建对象
    static {
        instance = new Singleton();
    }

    //3. 提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return instance;
    }

}