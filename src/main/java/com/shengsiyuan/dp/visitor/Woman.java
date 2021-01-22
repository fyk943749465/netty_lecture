package com.shengsiyuan.dp.visitor;


// 说明
// 1.这里我们使用到了双分派，即首先在客户端程序中，将具体的状态作为参数传递到Woman中，完成一次分派
// 2.然后Woman这个类中，调用了作为参数的＂具体方法＂
public class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
