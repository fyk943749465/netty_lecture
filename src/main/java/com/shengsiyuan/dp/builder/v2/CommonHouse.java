package com.shengsiyuan.dp.builder.v2;

public class CommonHouse extends HouseBuilder{
    @Override
    public void buildBasic() {
        house.setBasic("普通地基");
        System.out.println("普通房子大地基");
    }

    @Override
    public void buildWall() {
        house.setWall("普通墙");
        System.out.println("普通房子砌墙");
    }

    @Override
    public void roofed() {
        house.setRoofed("普通屋顶");
        System.out.println("普通房子普通屋顶");
    }
}
