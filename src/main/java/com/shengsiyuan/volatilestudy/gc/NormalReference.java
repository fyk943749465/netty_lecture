package com.shengsiyuan.volatilestudy.gc;

import java.io.IOException;

public class NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();

        System.in.read();
    }
}
