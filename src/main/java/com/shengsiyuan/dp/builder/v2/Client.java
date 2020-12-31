package com.shengsiyuan.dp.builder.v2;

public class Client {

    public static void main(String[] args) {

        // 盖普通房子
        CommonHouse commonHouse = new CommonHouse();
        // 准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);

        // 完成盖房子，返回产品（房子）
        House house = houseDirector.constructHouse();
        System.out.println(house.getBasic());

        houseDirector.setHouseBuilder(new HighBuilding());
        House highHouse = houseDirector.constructHouse();
        System.out.println(highHouse.getBasic());
    }
}
