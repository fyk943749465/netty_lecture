package com.shengsiyuan.dp.builder.v1;

public abstract class AbstractHouse {

    // 打地基
    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }
}
