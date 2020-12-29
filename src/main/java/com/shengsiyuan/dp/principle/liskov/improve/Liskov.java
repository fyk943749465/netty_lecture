package com.shengsiyuan.dp.principle.liskov.improve;

public class Liskov {
}


// 创建一个更加基础的基类
class Base {
    // 把更加基础的方法和成员写到Base类

}

// A类
class A extends Base {
    // 返回两个数的差
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

// B类继承了A
// 增加了一个新功能：完成两个数相加，然后和９求和
class B extends Base {
    // 如果B需要使用A类的方法，使用组合关系
    private A a = new A();


    // 这里，可能在无意识的情况下，重写了A类的方法
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int fun3(int a, int b) {
        return this.a.func1(a, b);
    }
}