package com.shengsiyuan.volatilestudy;

import org.openjdk.jol.info.ClassLayout;

public class TestJOL {

    public static void main(String[] args) {

        Object o = new Object();

        o.hashCode();

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
