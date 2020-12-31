package com.shengsiyuan.dp.builder.v2;

// 指挥者，这里会动态的指定指挥流程，返回具体产品
public class HouseDirector {

    HouseBuilder houseBuilder = null;


    // 构造器传入
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }


    // setter方法传入
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    // 如何处理建造房子的流程，交给指挥者
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWall();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
