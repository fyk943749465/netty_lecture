package com.shengsiyuan.dp.bridge;


// 折叠式手机类，继承了抽象类Phone
public class FoldedPhone extends Phone {

    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open() {
        super.open();
        System.out.println("折叠样式的手机");
    }

    public void close() {
        super.close();
        System.out.println("折叠样式的手机");
    }

    public void call() {
        super.call();
        System.out.println("折叠样式的手机");
    }
}
