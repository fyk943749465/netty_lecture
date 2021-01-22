package com.shengsiyuan.dp.bridge;

public class Client {

    public static void main(String[] args) {
        // 获取折叠式手机(样式　＋　品牌)

        Phone phone = new FoldedPhone(new XiaoMi());

        phone.open();
        phone.call();
        phone.close();
    }
}
