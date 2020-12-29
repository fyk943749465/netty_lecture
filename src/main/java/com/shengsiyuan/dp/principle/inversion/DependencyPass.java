package com.shengsiyuan.dp.principle.inversion;

public class DependencyPass {

    public static void main(String[] args) {
        //方式１　通过接口的方式进行依赖传递
//        ChangHong changHong = new ChangHong();
//        OpenAndClose openAndClose = new OpenAndClose();
//        openAndClose.open(changHong);

//        // 方式２ 通过构造方法进行依赖传递
//        ChangHong changHong = new ChangHong();
//        OpenAndClose openAndClose = new OpenAndClose(changHong);
//        openAndClose.open();

        //方式３　通过set方法进行依赖传递
        ChangHong changHong = new ChangHong();
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.setTv(changHong);
        openAndClose.open();
    }
}

//// 方式１，通过借口传递实现依赖
//// 开关的接口
//interface IOpenAndClose {
//    void open(ITV tv) ;
//}
//
//
//interface ITV {
//    void play();
//}
//
//class OpenAndClose implements IOpenAndClose {
//
//    @Override
//    public void open(ITV tv) {
//        tv.play();
//    }
//}
//
//class ChangHong implements ITV {
//
//    @Override
//    public void play() {
//        System.out.println("打开长虹电视机");
//    }
//}

//// 方式２：　通过构造方法依赖传递
//interface IOpenAndClose { //　抽象方法
//    void open();
//}
//
//interface ITV {  // ITV接口
//    void play();
//}
//
//class OpenAndClose implements IOpenAndClose {
//
//    private ITV tv;  //　成员
//
//    public OpenAndClose(ITV tv) {
//        this.tv = tv;
//    }
//
//    @Override
//    public void open() {
//        this.tv.play();
//    }
//}
//
//class ChangHong implements ITV {
//
//    @Override
//    public void play() {
//        System.out.println("打开长虹电视机");
//    }
//}


// 方式３，通过setter方法传递
interface IOpenAndClose {
    void open();
    void setTv(ITV tv);
}

interface ITV {
    void play();
}

class OpenAndClose implements IOpenAndClose {

    private ITV tv;

    @Override
    public void open() {
        this.tv.play();
    }

    @Override
    public void setTv(ITV tv) {
        this.tv = tv;
    }
}

class ChangHong implements ITV {

    @Override
    public void play() {
        System.out.println("打开长虹电视机");
    }
}