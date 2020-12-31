package com.shengsiyuan.dp.builder.v2;

public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBasic() {
        house.setBasic("高楼地基");
        System.out.println("高楼打地基");
    }

    @Override
    public void buildWall() {
        house.setWall("高楼墙");
        System.out.println("高楼砌墙");
    }

    @Override
    public void roofed() {
        house.setRoofed("高楼屋顶");
        System.out.println("高楼造屋顶，透明屋顶");
    }
}
