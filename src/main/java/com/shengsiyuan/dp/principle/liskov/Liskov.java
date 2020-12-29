package com.shengsiyuan.dp.principle.liskov;

public class Liskov {

    public static void main(String[] args) {

        A a = new A();
        a.fun1(10, 8);

        System.out.println("--------------");
        B b = new B();
        b.fun1(10, 8);
        b.fun1(10, 8);
    }
}

// A类
class A {

    public int fun1(int a, int b) {
        return a - b;
    }
}

//
class B extends A {
    public int fun1(int a, int b) {
        return a + b;
    }

    public int fun2(int a, int b) {
        return a * b;
    }
}
