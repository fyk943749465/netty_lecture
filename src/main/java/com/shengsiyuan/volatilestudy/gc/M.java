package com.shengsiyuan.volatilestudy.gc;

public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
