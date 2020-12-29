package com.shengsiyuan.volatilestudy.gc;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void main(String[] args) {

        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> t1 = new ThreadLocal<>();
        t1.set(new M()); // 弱引用的作用，防止内存泄露
        t1.remove();  //如果threadlocal对象没用了，那么要调用remove，否则又会产生内存泄露
    }
}
